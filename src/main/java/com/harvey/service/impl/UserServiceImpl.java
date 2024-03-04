package com.harvey.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.harvey.anno.AddOperationLog;
import com.harvey.common.RedisKey;
import com.harvey.common.Result;
import com.harvey.convert.UserDtoOperationLogConvert;
import com.harvey.domain.Activity;
import com.harvey.domain.Clue;
import com.harvey.domain.User;
import com.harvey.dto.PageDto;
import com.harvey.dto.UserDto;
import com.harvey.mapper.UserMapper;
import com.harvey.service.UserService;
import com.harvey.vo.PageVo;
import com.harvey.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // Feat: Caching data by Redis
    // @Override
    // public Result getUserListByPage(PageDto pageDto) {
    //     String key = "user:list:page:" + pageDto.getPageNo() + ":" + pageDto.getPageSize();
    //
    //     PageVo<UserVo> pageVo = (PageVo<UserVo>) redisTemplate.opsForValue().get(key);
    //     if (pageVo != null) {
    //         return Result.success(pageVo);
    //     }
    //
    //     Page<User> page = new Page(pageDto.getPageNo(), pageDto.getPageSize());
    //     page.addOrder(new OrderItem("id", true));
    //     Db.lambdaQuery(User.class)
    //         .select(
    //             User::getId,
    //             User::getUsername,
    //             User::getPhone,
    //             User::getEmail,
    //             User::getCreateTime
    //         )
    //         .page(page);
    //
    //     pageVo = new PageVo<>();
    //     pageVo.setList(page.getRecords()
    //         .stream()
    //         .map((user) -> BeanUtil.copyProperties(user, UserVo.class))
    //         .toList());
    //     pageVo.setTotalSize(page.getTotal());
    //     pageVo.setTotalPages(page.getPages());
    //
    //     redisTemplate.opsForValue().set(key, pageVo);
    //
    //     return Result.success(pageVo);
    // }
    
    // Feat: Caching data by SpringCache
    @Override
    @Cacheable(cacheNames = RedisKey.USER_LIST_PAGE, key = "#pageDto.pageNo + ':' + #pageDto.pageSize")
    public Result getUserByPage(PageDto pageDto) {
        Page<User> page = new Page(pageDto.getPageNo(), pageDto.getPageSize());
        Db.lambdaQuery(User.class)
            .select(
                User::getId,
                User::getUsername,
                User::getPhone,
                User::getEmail,
                User::getCreateTime
            )
            .page(page);
        
        PageVo<UserVo> pageVo = new PageVo<>();
        pageVo.setList(page.getRecords()
            .stream()
            .map((user) -> BeanUtil.copyProperties(user, UserVo.class))
            .toList());
        pageVo.setTotalSize(page.getTotal());
        pageVo.setTotalPages(page.getPages());
        
        return Result.success(pageVo);
    }
    
    @Override
    @Cacheable(cacheNames = RedisKey.USER, key = "#id")
    public Result getUserById(Integer id) {
        User user = Db.getById(id, User.class);
        UserVo userVo = BeanUtil.copyProperties(user, UserVo.class);
        return Result.success(userVo);
    }

    // Feat: Add operation log with AOP and Strategy Pattern
    @AddOperationLog(desc = "Upd user", convert = UserDtoOperationLogConvert.class)
    @Override
    public Result updUserById(UserDto userDto) {
        User holder = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = BeanUtil.copyProperties(userDto, User.class);
        user.setEditTime(DateTime.now());
        user.setEditBy(holder.getId());
        Db.updateById(user);
        redisTemplate.delete(RedisKey.USER + "::" + user.getId());
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(RedisKey.USER_LIST_PAGE + "*")));
        return Result.success();
    }

    @AddOperationLog(desc = "Add user", convert = UserDtoOperationLogConvert.class)
    @Override
    public Result addUser(UserDto userDto) {
        User holder = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = BeanUtil.copyProperties(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCreateTime(DateTime.now());
        user.setCreateBy(holder.getId());
        user.setEditTime(DateTime.now());
        user.setEditBy(holder.getId());
        Db.save(user);
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(RedisKey.USER_LIST_PAGE + "*")));
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(RedisKey.USER_LIST_PAGE + "*")));
        return Result.success();
    }
    
    @Override
    @Transactional
    public Result delUserById(Integer id) {
        Db.lambdaUpdate(Activity.class)
            .eq(Activity::getUserId, id)
            .remove();
        Db.lambdaUpdate(Clue.class)
            .eq(Clue::getUserId, id)
            .remove();
        Db.lambdaUpdate(User.class)
            .eq(User::getId, id)
            .remove();
        redisTemplate.delete(RedisKey.USER + "::" + id);
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(RedisKey.USER_LIST_PAGE + "*")));
        return Result.success();
    }
    
    @Override
    @Transactional
    public Result delUserByIdList(List<Integer> idList) {
        Db.lambdaUpdate(Activity.class)
            .in(Activity::getUserId, idList)
            .remove();
        Db.lambdaUpdate(Clue.class)
            .in(Clue::getUserId, idList)
            .remove();
        Db.lambdaUpdate(User.class)
            .in(User::getId, idList)
            .remove();
        redisTemplate.delete(idList.stream().map((id) -> RedisKey.USER + "::" + id).toList());
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(RedisKey.USER_LIST_PAGE + "*")));
        return Result.success();
    }
}





package com.harvey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harvey.common.Result;
import com.harvey.domain.User;
import com.harvey.dto.PageDto;
import com.harvey.dto.UserDto;

import java.util.List;

public interface UserService extends IService<User> {
    Result getUserByPage(PageDto pageDto);
    
    Result getUserById(Integer id);
    
    Result updUserById(UserDto userDto);
    
    Result addUser(UserDto userDto);
    
    Result delUserById(Integer id);
    
    Result delUserByIdList(List<Integer> idList);
}

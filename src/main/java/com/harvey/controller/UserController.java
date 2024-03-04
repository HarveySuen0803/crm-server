package com.harvey.controller;

import com.harvey.common.Result;
import com.harvey.dto.PageDto;
import com.harvey.dto.UserDto;
import com.harvey.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ALL_USER', 'GET_USER')")
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/page")
    public Result getUserByPage(@RequestBody PageDto pageDto) {
        log.info("getUserByPage, {}", pageDto);
        return userService.getUserByPage(pageDto);
    }
    
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id") Integer id) {
        log.info("getUserById, {}", id);
        return userService.getUserById(id);
    }
    
    @PreAuthorize("hasAnyAuthority('ALL_USER', 'ADD_USER')")
    @PostMapping
    public Result addUser(@RequestBody UserDto userDto) {
        log.info("addUser, {}", userDto);
        return userService.addUser(userDto);
    }
    
    @PreAuthorize("hasAnyAuthority('ALL_USER', 'UPD_USER')")
    @PutMapping
    public Result updUserById(@RequestBody UserDto userDto) {
        log.info("updUserById, {}", userDto);
        return userService.updUserById(userDto);
    }
    
    @PreAuthorize("hasAnyAuthority('ALL_USER', 'DEL_USER')")
    @DeleteMapping("/{id}")
    public Result delUserById(@PathVariable("id") Integer id) {
        log.info("delUserById, {}", id);
        return userService.delUserById(id);
    }
    
    @PreAuthorize("hasAnyAuthority('ALL_USER', 'DEL_USER')")
    @DeleteMapping
    public Result delUserByIdList(@RequestBody List<Integer> idList) {
        log.info("delUserByIdList, {}", idList);
        return userService.delUserByIdList(idList);
    }
}

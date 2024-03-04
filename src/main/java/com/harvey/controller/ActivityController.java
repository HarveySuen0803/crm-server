package com.harvey.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ALL_ACTIVITY', 'GET_ACTIVITY')")
public class ActivityController {
}

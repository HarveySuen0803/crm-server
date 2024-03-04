package com.harvey.common;

import java.util.concurrent.TimeUnit;

public class RedisKey {
    public static final String LOGIN_TOKEN = "token:login";
    public static final Long LOGIN_TOKEN_S_TTL = 60 * 24 * 7L;
    public static final Long LOGIN_TOKEN_L_TTL = 60 * 24 * 30L;
    public static final TimeUnit LOGIN_TOKEN_TTL_UNIT = TimeUnit.MINUTES;
    
    public static final String USER_LIST_PAGE = "user:list:page";
    
    public static final String USER = "user";
    public static final String CLUE = "clue";
    public static final String ACTIVITY = "activity";
    public static final String ROLE = "role";
}

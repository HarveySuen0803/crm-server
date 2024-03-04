package com.harvey.convert;

import com.harvey.domain.OperationLog;
import com.harvey.dto.UserDto;

public class UserDtoOperationLogConvert implements OperationLogConvert<UserDto> {
    @Override
    public OperationLog convert(UserDto userDto) {
        OperationLog operationLog = new OperationLog();
        operationLog.setUserId(userDto.getId());
        return operationLog;
    }
}

package com.dronestore.dronestore.mapper;

import com.dronestore.dronestore.data.dto.UserResponseDto;
import com.dronestore.dronestore.data.User;
import org.springframework.stereotype.Service;

@Service
public class DataMapper {
    public UserResponseDto mapUserToUserResponseDto(User user) {
        UserResponseDto rv = new UserResponseDto();
        rv.setMessage("Hello");
        rv.setName(user.getName());
        return rv;
    }
}

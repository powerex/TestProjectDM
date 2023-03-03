package com.dronestore.dronestore.controller;

import com.dronestore.dronestore.data.User;
import com.dronestore.dronestore.data.dto.UserResponseDto;
import com.dronestore.dronestore.data.dto.UserSaveRequestDto;
import com.dronestore.dronestore.exceptions.UserNotFoundException;
import com.dronestore.dronestore.mapper.DataMapper;
import com.dronestore.dronestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/v0/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DataMapper dataMapper;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        List<UserResponseDto> rv = userService.getAllUsers()
            .stream()
            .map(u -> dataMapper.mapUserToUserResponseDto(u))
            .toList();
        return rv;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserSaveRequestDto userDto) {
        User user = userService.saveUser(userDto);
        UserResponseDto responseDto = dataMapper.mapUserToUserResponseDto(user);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        try {
            User user = userService.getUserByEmail(email);
            UserResponseDto responseDto = dataMapper.mapUserToUserResponseDto(user);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(new UserResponseDto(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUserById(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserSaveRequestDto userDto) {
        try {
            User user = userService.updateUser(userDto);
            UserResponseDto responseDto = dataMapper.mapUserToUserResponseDto(user);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(new UserResponseDto(), HttpStatus.NOT_FOUND);
        }
    }
}

package com.dronestore.dronestore.services;

import com.dronestore.dronestore.data.User;
import com.dronestore.dronestore.data.dto.UserSaveRequestDto;
import com.dronestore.dronestore.exceptions.UserNotFoundException;
import com.dronestore.dronestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String email) {
        return userRepository.findByEmail(email).orElse(new User("", email, "Unknown"));
    }

    public User saveUser(UserSaveRequestDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return userRepository.findByEmail(email).get();
        } else {
            throw new UserNotFoundException("User with email: " + email + " not found");
        }
    }

    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        userRepository.deleteById(user.getId());
    }

    public User updateUser(UserSaveRequestDto userDto) {
        String email = userDto.getEmail();
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();
            user.setEmail(email); // TODO check if email available
            user.setName(userDto.getName());
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User with email: " + email + " not found");
        }
    }
}

package com.ynov.sprintify.services;

import com.ynov.sprintify.dto.UserDTO;
import com.ynov.sprintify.exceptions.users.UserNotFound;
import com.ynov.sprintify.models.User;
import com.ynov.sprintify.repositories.UserRepository;
import com.ynov.sprintify.utils.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean userExists(String username) {
        return userRepository.existsUserByUsername(username);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFound::new);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public UserDTO getUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFound::new);
        return UserMapper.userToUserDTO(user);
    }

    public List<UserDTO> getUsersFiltered(String prefix) {
        List<User> users = userRepository.findByUsernameStartingWithIgnoreCase(prefix);
        return users.stream().map(UserMapper::userToUserDTO).toList();
    }

}

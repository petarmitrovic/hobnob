package com.neperix.hobnob.iam;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

/**
 * @author petarmitrovic
 */
@Component
@AllArgsConstructor
class UserService {

    private final UserRepository userRepository;

    List<User> allUsers() {
        return this.userRepository.findAll();
    }

    void createNew(String uuid, String username, String email, Set<String> roles) {
        User newUser = User.builder()
                .uuid(uuid)
                .username(username)
                .email(email)
                .roles(roles)
                .build();
        this.userRepository.save(newUser);
    }
}

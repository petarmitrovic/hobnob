package com.neperix.hobnob.iam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
class UserService {

    private final UserRepository userRepository;

    List<User> allUsers() {
        return this.userRepository.findAll();
    }

    Optional<User> singleUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    User createNew(String username, String email, Set<String> roles) {
        User newUser = User.builder()
                .username(username)
                .email(email)
                .roles(roles)
                .build();
        return this.userRepository.save(newUser);
    }
}

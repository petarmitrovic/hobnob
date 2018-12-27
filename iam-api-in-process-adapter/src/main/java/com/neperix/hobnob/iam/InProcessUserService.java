package com.neperix.hobnob.iam;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.neperix.hobnob.iam.api.User;
import com.neperix.hobnob.iam.api.UserApi;

import lombok.AllArgsConstructor;

/**
 * @author petarmitrovic
 */
@Component
@AllArgsConstructor
public class InProcessUserService implements UserApi {

    private final UserService userService;

    @Override
    public List<User> all() {
        return this.userService.allUsers().stream()
                .map(user -> new User(user.getUsername(), user.getRoles()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findOne(String uuid) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userService.singleUser(username)
                .map(user -> new User(user.getUsername(), user.getRoles()));
    }

    @Override
    public User create(String username, String email, Set<String> roles) {
        com.neperix.hobnob.iam.User user = this.userService.createNew(username, email, roles);
        return new User(user.getUsername(), user.getRoles());
    }
}

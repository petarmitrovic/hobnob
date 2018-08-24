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
                .map(user -> new User(user.getUuid(), user.getUsername(), user.getRoles()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findOne(String uuid) {
        return Optional.empty();
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void create(String uuid, String username, String email, Set<String> roles) {
        this.userService.createNew(uuid, username, email, roles);
    }
}

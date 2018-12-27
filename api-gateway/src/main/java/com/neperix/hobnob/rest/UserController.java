package com.neperix.hobnob.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neperix.hobnob.iam.api.User;
import com.neperix.hobnob.iam.api.UserApi;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserApi userApi;

    @GetMapping
    public List<User> allUsers() {
        return userApi.all();
    }

    @GetMapping(value = "/{username:.+}")
    public ResponseEntity<User> userById(@PathVariable("username") String username) {
        return userApi.findByUsername(username)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity newUser(@RequestBody CreateUserCommand user) {
        User createdUser = userApi.create(user.getUsername(), user.getEmail(), user.getRoles());
        return ResponseEntity.ok(createdUser);
    }
}

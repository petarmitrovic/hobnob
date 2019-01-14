package com.neperix.hobnob.security;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.neperix.hobnob.iam.api.User;
import com.neperix.hobnob.iam.api.UserApi;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class IamAuthenticatedUserRepository implements AuthenticatedUserRepository {

    private final UserApi userApi;

    @Override
    public Optional<AuthenticatedUser> findByUsername(String username) {
        return this.userApi.findByUsername(username)
                .map(IamAuthUser::new);
    }

    @AllArgsConstructor
    private static final class IamAuthUser implements AuthenticatedUser {

        private final User user;

        @Override
        public String username() {
            return user.getUsername();
        }

        @Override
        public Set<String> roles() {
            return user.getRoles();
        }
    }
}

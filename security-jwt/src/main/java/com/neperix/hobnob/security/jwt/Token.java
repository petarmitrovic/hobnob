package com.neperix.hobnob.security.jwt;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Token {

    private String username;
    private LocalDateTime expiresAt;
}

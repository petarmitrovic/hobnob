package com.neperix.hobnob.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "hobnob.security.jwt")
@Data
public class JwtProperties {

    private String issuer;
    private String secret;
    private long timeToLiveInHours;
}

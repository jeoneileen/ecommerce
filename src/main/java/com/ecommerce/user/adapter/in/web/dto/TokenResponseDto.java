package com.ecommerce.user.adapter.in.web.dto;

public class TokenResponseDto {

    private final String accessToken;

    public TokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

package com.cafe.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

import com.cafe.dtos.auths.TokenResponse;

public interface ITokenService {
    TokenResponse generateTokens(UserDetails userDetails);
    TokenResponse refreshToken(String refreshToken);
}

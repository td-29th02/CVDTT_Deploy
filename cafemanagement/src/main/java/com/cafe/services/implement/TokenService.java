package com.cafe.services.implement;

import com.cafe.dtos.auths.TokenResponse;
import com.cafe.security.JwtTokenProvider;
import com.cafe.services.interfaces.ITokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements ITokenService {
    private final JwtTokenProvider tokenProvider;

    // Constructor injection
    public TokenService(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public TokenResponse generateTokens(UserDetails userDetails) {
        String accessToken = tokenProvider.generateAccessToken(userDetails);
        String refreshToken = tokenProvider.generateRefreshToken(userDetails);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(tokenProvider.extractExpiration(accessToken).getTime())
                .build();
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        // Validate refresh token
        if (!tokenProvider.validateToken(refreshToken, null)) {
            throw new RuntimeException("Invalid refresh token");
        }

        // Get user details from refresh token
        //String username = tokenProvider.extractUsername(refreshToken);
        // TODO: Load user details from database using username

        // Generate new tokens
        return generateTokens(null); // TODO: Pass actual user details
    }
}

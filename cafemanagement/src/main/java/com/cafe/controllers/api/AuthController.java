package com.cafe.controllers.api;

import com.cafe.dtos.auths.LoginRequest;
import com.cafe.dtos.auths.TokenResponse;
import com.cafe.global.ApiResult;
import com.cafe.services.interfaces.ITokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication")
public class AuthController extends ApiBaseController {
    private final AuthenticationManager authenticationManager;
    private final ITokenService tokenService;

    // Constructor injection
    public AuthController(AuthenticationManager authenticationManager, ITokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResult<TokenResponse>> login(@RequestBody LoginRequest loginRequest) {
        return execute(() -> {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return tokenService.generateTokens(userDetails);
        }, "Đăng nhập thành công");
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResult<TokenResponse>> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        return execute(() -> {
            String token = refreshToken;
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            return tokenService.refreshToken(token);
        }, "Làm mới token thành công");
    }
} 
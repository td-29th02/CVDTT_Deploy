package com.cafe.controllers.api;

import com.cafe.dtos.configs.ConfigRequest;
import com.cafe.repositories.AppSettingRepository;
import com.cafe.services.implement.ConfigService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Config Management")
@RequestMapping("/api/v1/config")

public class ConfigController {
    private final AppSettingRepository appSettingRepository;

    // Constructor injection
    public ConfigController(AppSettingRepository appSettingRepository) {
        this.appSettingRepository = appSettingRepository;
    }

    @GetMapping
    public ConfigRequest getStoreConfig() {
        ConfigService config = ConfigService.getInstance(appSettingRepository);
        ConfigRequest dto = new ConfigRequest();
        dto.setStoreName(config.getStoreName());
        dto.setAddress(config.getAddress());
        dto.setPhone(config.getPhone());
        dto.setEmail(config.getEmail());
        dto.setTaxCode(config.getTaxCode());
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ConfigRequest updateStoreConfig(@RequestBody ConfigRequest newConfig) {
        ConfigService config = ConfigService.getInstance(appSettingRepository);
        config.setStoreName(newConfig.getStoreName());
        config.setAddress(newConfig.getAddress());
        config.setPhone(newConfig.getPhone());
        config.setEmail(newConfig.getEmail());
        config.setTaxCode(newConfig.getTaxCode());
        
        ConfigRequest dto = new ConfigRequest();
        dto.setStoreName(config.getStoreName());
        dto.setAddress(config.getAddress());
        dto.setPhone(config.getPhone());
        dto.setEmail(config.getEmail());
        dto.setTaxCode(config.getTaxCode());
        return dto;
    }
} 
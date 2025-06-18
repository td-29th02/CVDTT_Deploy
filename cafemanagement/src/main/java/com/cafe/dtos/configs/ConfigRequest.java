package com.cafe.dtos.configs;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PUBLIC)
public class ConfigRequest {
    String storeName;
    String address;
    String phone;
    String email;
    String taxCode;
} 
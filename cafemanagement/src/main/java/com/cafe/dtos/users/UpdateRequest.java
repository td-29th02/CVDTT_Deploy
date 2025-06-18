package com.cafe.dtos.users;

import lombok.experimental.FieldDefaults;

@FieldDefaults(level = lombok.AccessLevel.PUBLIC)
public class UpdateRequest {
    String username;
    String role;
}

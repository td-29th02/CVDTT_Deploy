package com.cafe.dtos.users;

import java.time.LocalDate;


import lombok.experimental.FieldDefaults;

@FieldDefaults(level = lombok.AccessLevel.PUBLIC)
public class CreateRequest {
    String username;
    String password;
    String role;
    LocalDate createdDate = LocalDate.now();
}

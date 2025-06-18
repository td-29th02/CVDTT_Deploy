package com.cafe.dtos.tokens;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PUBLIC)
public class CreateRequest {
    String username = "";
    String password = "";
}

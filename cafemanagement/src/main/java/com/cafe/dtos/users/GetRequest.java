package com.cafe.dtos.users;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

import com.cafe.services.patterns.builders.GetRequestBuilder;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PUBLIC)
public class GetRequest {
    Integer id;
    String username;
    String role;
    LocalDate createdDate;
    
    public static GetRequestBuilder builder() {
        return new GetRequestBuilder();
    }
}
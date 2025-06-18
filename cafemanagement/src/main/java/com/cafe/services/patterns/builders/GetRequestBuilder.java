package com.cafe.services.patterns.builders;

import java.time.LocalDate;

import com.cafe.dtos.users.GetRequest;

public class GetRequestBuilder {
    private Integer id;
    private String username;
    private String role;
    private LocalDate createdDate;
        
    public GetRequestBuilder id(Integer id) {
        this.id = id;
        return this;
    }
    
    public GetRequestBuilder username(String username) {
        this.username = username;
        return this;
    }
    
    public GetRequestBuilder role(String role) {
        this.role = role;
        return this;
    }
    
    public GetRequestBuilder createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }
    
    public GetRequest build() {
        return new GetRequest(id, username, role, createdDate);
    }
}

package com.cafe.services.patterns.builders;

import com.cafe.entities.User;
import com.cafe.entities.Order;
import com.cafe.entities.ViewConfig;

import java.time.LocalDate;
import java.util.List;

public class UserBuilder {
    private Integer id;
    private String username;
    private String password;
    private String role;
    private LocalDate createdDate;
    private List<Order> orders;
    private List<ViewConfig> viewConfigs;

    public UserBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder role(String role) {
        this.role = role;
        return this;
    }

    public UserBuilder createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public UserBuilder orders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public UserBuilder viewConfigs(List<ViewConfig> viewConfigs) {
        this.viewConfigs = viewConfigs;
        return this;
    }

    public User build() {
        return new User(id, username, password, role, createdDate, orders, viewConfigs);
    }
}

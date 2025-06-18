package com.cafe.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.cafe.services.patterns.builders.UserBuilder;

@Entity
@Table(name = "Users")
@Data //Tự động tạo getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor //Tạo constructor không tham số
@AllArgsConstructor //Tạo constructor với tất cả các tham số
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @Column(length = 50, nullable = false, unique = true)
    String username;
    
    @Column(length = 255, nullable = false)
    String password;
    
    @Column(length = 20, nullable = false)
    String role;
    
    LocalDate createdDate;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Order> orders;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<ViewConfig> viewConfigs;
    
    public static UserBuilder builder() {
        return new UserBuilder();
    }
}
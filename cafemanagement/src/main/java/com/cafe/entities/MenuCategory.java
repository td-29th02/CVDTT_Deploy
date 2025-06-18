package com.cafe.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "MenuCategories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class MenuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @Column(length = 50, nullable = false)
    String name; // 'Hot', 'Cold'
    
    @Column(length = 255)
    String description;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<MenuItem> menuItems;
}
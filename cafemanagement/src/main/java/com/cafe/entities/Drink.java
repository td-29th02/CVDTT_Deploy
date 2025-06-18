package com.cafe.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Drinks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Drink {
    
    // Constructor với các trường cơ bản
    public Drink(String name, String type, BigDecimal basePrice, String description) {
        this.name = name;
        this.type = type;
        this.basePrice = basePrice;
        this.description = description;
        this.isActive = true; // mặc định là true
    }

    // Constructor đầy đủ (không bao gồm ID và relationships)
    public Drink(String name, String type, BigDecimal basePrice, String description, 
                 String brewingMethod, String origin, Integer caffeineLevel, Boolean isActive) {
        this.name = name;
        this.type = type;
        this.basePrice = basePrice;
        this.description = description;
        this.brewingMethod = brewingMethod;
        this.origin = origin;
        this.caffeineLevel = caffeineLevel;
        this.isActive = isActive != null ? isActive : true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @Column(length = 100, nullable = false)
    String name;
    
    @Column(length = 20, nullable = false)
    String type; // 'Coffee', 'Tea'
    
    @Column(name = "BasePrice", precision = 10, scale = 2, nullable = false)
    BigDecimal basePrice;
    
    @Column(length = 255)
    String description;
    
    @Column(length = 50)
    String brewingMethod; // Espresso, Drip, French Press cho Coffee
    
    @Column(length = 50)
    String origin; // Origin của Coffee hoặc Tea
    
    @Column
    Integer caffeineLevel; // 1-5 scale
    
    @Column(name = "IsActive")
    Boolean isActive = true; // Mặc định là true nhưng có thể thay đổi
    
    @OneToMany(mappedBy = "drink", cascade = CascadeType.ALL)
    List<MenuItem> menuItems;
    
    @OneToMany(mappedBy = "drink", cascade = CascadeType.ALL)
    List<OrderDetail> orderDetails;
    
    @OneToMany(mappedBy = "drink", cascade = CascadeType.ALL)
    List<ComboItem> comboItems;
}
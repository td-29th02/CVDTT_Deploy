package com.cafe.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "Toppings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @Column(length = 50, nullable = false)
    String name; // 'Milk', 'Sugar', 'Ice'
    
    @Column(precision = 10, scale = 2, nullable = false)
    BigDecimal price;
    
    @Column(name = "IsActive")
    Boolean isActive = true;
}
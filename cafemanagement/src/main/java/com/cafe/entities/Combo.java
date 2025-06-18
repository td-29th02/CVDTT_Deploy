package com.cafe.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Combos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @Column(length = 100, nullable = false)
    String name;
    
    @Column(length = 255)
    String description;
    
    @Column(name = "DiscountPercent", precision = 5, scale = 2)
    BigDecimal discountPercent = BigDecimal.ZERO;
    
    @Column(name = "IsActive")
    Boolean isActive = true;
    
    @Column(name = "CreatedDate")
    LocalDateTime createdDate = LocalDateTime.now();
    
    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL)
    List<ComboItem> comboItems;
    
    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL)
    List<OrderDetail> orderDetails;
}
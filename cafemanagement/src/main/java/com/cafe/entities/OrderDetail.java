package com.cafe.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "OrderDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @ManyToOne
    @JoinColumn(name = "OrderId", nullable = false)
    Order order;
    
    @ManyToOne
    @JoinColumn(name = "DrinkId", nullable = false)
    Drink drink;
    
    @Column(nullable = false)
    Integer quantity = 1;
    
    @Column(name = "UnitPrice", precision = 10, scale = 2, nullable = false)
    BigDecimal unitPrice;
    
    @Column(name = "ToppingsJson", columnDefinition = "NVARCHAR(MAX)")
    String toppingsJson; // Lưu danh sách topping dạng JSON
    
    @ManyToOne
    @JoinColumn(name = "ComboId")
    Combo combo;
}
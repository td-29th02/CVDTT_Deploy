package com.cafe.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "ComboItems")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ComboItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @ManyToOne
    @JoinColumn(name = "ComboId", nullable = false)
    Combo combo;
    
    @ManyToOne
    @JoinColumn(name = "DrinkId", nullable = false)
    Drink drink;
    
    @Column(nullable = false)
    Integer quantity = 1;
}

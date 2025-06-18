package com.cafe.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "MenuItems")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class MenuItem {
    public MenuItem(Drink drink, MenuCategory category, Integer displayOrder) {
        this.drink = drink;
        this.category = category;
        this.displayOrder = displayOrder;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @ManyToOne
    @JoinColumn(name = "DrinkId", nullable = false)
    Drink drink;
    
    @ManyToOne
    @JoinColumn(name = "CategoryId", nullable = false)
    MenuCategory category;
    
    @Column(name = "DisplayOrder")
    Integer displayOrder = 0;
    
    @Column(name = "IsAvailable")
    Boolean isAvailable = true;
}
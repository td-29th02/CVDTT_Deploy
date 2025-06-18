package com.cafe.services.patterns.factory;

import java.math.BigDecimal;

import com.cafe.entities.Drink;

public abstract class DrinkFactory {
    public abstract Drink createDrink(String name, BigDecimal basePrice, String description);
    
    public static DrinkFactory getFactory(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Drink type cannot be null");
        }
        
        switch (type.toLowerCase()) {
            case "coffee":
                return new CoffeeFactory();
            case "tea":
                return new TeaFactory();
            default:
                throw new IllegalArgumentException("Unknown drink type: " + type);
        }
    }
}

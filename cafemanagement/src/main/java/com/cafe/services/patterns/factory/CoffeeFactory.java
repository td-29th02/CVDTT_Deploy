package com.cafe.services.patterns.factory;

import java.math.BigDecimal;

import com.cafe.entities.Drink;

public class CoffeeFactory extends DrinkFactory {
    @Override
    public Drink createDrink(String name, BigDecimal basePrice, String description) {
        Drink coffee = new Drink(name, "Coffee", basePrice, description);
        
        // Set default coffee properties
        coffee.setBrewingMethod("Espresso");
        coffee.setOrigin("Colombia");
        coffee.setCaffeineLevel(4); // High caffeine
        
        // Customize based on coffee type
        if (name.toLowerCase().contains("espresso")) {
            coffee.setBrewingMethod("Espresso");
            coffee.setCaffeineLevel(5);
        } else if (name.toLowerCase().contains("americano")) {
            coffee.setBrewingMethod("Drip");
            coffee.setCaffeineLevel(3);
        } else if (name.toLowerCase().contains("cappuccino")) {
            coffee.setBrewingMethod("Espresso");
            coffee.setCaffeineLevel(4);
        }
        
        return coffee;
    }
}

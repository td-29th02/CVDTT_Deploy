package com.cafe.services.patterns.factory;

import java.math.BigDecimal;

import com.cafe.entities.Drink;

public class TeaFactory extends DrinkFactory {
    @Override
    public Drink createDrink(String name, BigDecimal basePrice, String description) {
        Drink tea = new Drink(name, "Tea", basePrice, description);
        
        // Set default tea properties
        tea.setBrewingMethod("Steeping");
        tea.setOrigin("China");
        tea.setCaffeineLevel(2); // Low caffeine
        
        // Customize based on tea type
        if (name.toLowerCase().contains("green")) {
            tea.setOrigin("Japan");
            tea.setCaffeineLevel(2);
        } else if (name.toLowerCase().contains("black")) {
            tea.setOrigin("India");
            tea.setCaffeineLevel(3);
        } else if (name.toLowerCase().contains("herbal")) {
            tea.setCaffeineLevel(0); // No caffeine
        }
        
        return tea;
    }
}

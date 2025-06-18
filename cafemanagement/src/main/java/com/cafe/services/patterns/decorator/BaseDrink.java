package com.cafe.services.patterns.decorator;

import java.math.BigDecimal;

import com.cafe.entities.Drink;

public class BaseDrink implements DrinkItem {
    private Drink drink;
    
    public BaseDrink(Drink drink) {
        this.drink = drink;
    }
    
    @Override
    public String getDescription() {
        return drink.getName();
    }
    
    @Override
    public BigDecimal getPrice() {
        return drink.getBasePrice();
    }
    
    @Override
    public Integer getDrinkId() {
        return drink.getId();
    }
    
    public Drink getDrink() {
        return drink;
    }
}

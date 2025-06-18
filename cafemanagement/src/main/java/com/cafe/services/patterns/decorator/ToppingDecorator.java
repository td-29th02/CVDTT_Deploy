package com.cafe.services.patterns.decorator;

import java.math.BigDecimal;

public abstract class ToppingDecorator implements DrinkItem {
    protected DrinkItem drinkItem;
    
    public ToppingDecorator(DrinkItem drinkItem) {
        this.drinkItem = drinkItem;
    }
    
    @Override
    public String getDescription() {
        return drinkItem.getDescription();
    }
    
    @Override
    public BigDecimal getPrice() {
        return drinkItem.getPrice();
    }
    
    @Override
    public Integer getDrinkId() {
        return drinkItem.getDrinkId();
    }
}

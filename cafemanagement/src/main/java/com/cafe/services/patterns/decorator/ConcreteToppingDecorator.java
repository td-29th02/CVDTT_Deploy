package com.cafe.services.patterns.decorator;

import java.math.BigDecimal;

import com.cafe.entities.Topping;

public class ConcreteToppingDecorator extends ToppingDecorator {
    private Topping topping;
    
    public ConcreteToppingDecorator(DrinkItem drinkItem, Topping topping) {
        super(drinkItem);
        this.topping = topping;
    }
    
    @Override
    public String getDescription() {
        return drinkItem.getDescription() + " + " + topping.getName();
    }
    
    @Override
    public BigDecimal getPrice() {
        return drinkItem.getPrice().add(topping.getPrice());
    }
    
    public Topping getTopping() {
        return topping;
    }
}

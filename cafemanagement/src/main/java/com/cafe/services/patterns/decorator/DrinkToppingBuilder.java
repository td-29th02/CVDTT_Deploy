package com.cafe.services.patterns.decorator;

import java.util.List;

import com.cafe.entities.Drink;
import com.cafe.entities.Topping;

public class DrinkToppingBuilder {
    
    public static DrinkItem buildDrinkWithToppings(Drink drink, List<Topping> toppings) {
        DrinkItem drinkItem = new BaseDrink(drink);
        
        // Apply each topping as a decorator
        for (Topping topping : toppings) {
            drinkItem = new ConcreteToppingDecorator(drinkItem, topping);
        }
        
        return drinkItem;
    }
}

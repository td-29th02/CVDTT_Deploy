package com.cafe.services.interfaces;

import java.util.List;

import com.cafe.dtos.topping.AddToppingsDto;
import com.cafe.dtos.topping.CreateToppingDto;
import com.cafe.dtos.topping.DrinkWithToppingsDto;
import com.cafe.dtos.topping.ToppingResponseDto;

public interface IToppingService {
    public ToppingResponseDto createTopping(CreateToppingDto request);
    
    public List<ToppingResponseDto> getAllToppings();
    
    public ToppingResponseDto getToppingById(Integer id);
    
    public ToppingResponseDto updateTopping(Integer id, CreateToppingDto request);
    
    public void deleteTopping(Integer id);
    
    public DrinkWithToppingsDto addToppingsToDrink(AddToppingsDto request);
}
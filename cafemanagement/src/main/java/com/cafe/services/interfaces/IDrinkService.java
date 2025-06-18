package com.cafe.services.interfaces;

import java.util.List;

import com.cafe.dtos.drinks.CreateDrinkDto;
import com.cafe.dtos.drinks.DrinkResponseDto;

public interface IDrinkService {
    public DrinkResponseDto createDrink(CreateDrinkDto request);

    public List<DrinkResponseDto> getAllDrinks();
    
    public List<DrinkResponseDto> getAllDrinksIncludeInactive();

    public List<DrinkResponseDto> getDrinksByType(String type);

    public DrinkResponseDto getDrinkById(Integer id);

    public DrinkResponseDto updateDrink(Integer id, CreateDrinkDto request);

    public void deleteDrink(Integer id);
    
    public DrinkResponseDto restoreDrink(Integer id);
    
    public void permanentDeleteDrink(Integer id);
}
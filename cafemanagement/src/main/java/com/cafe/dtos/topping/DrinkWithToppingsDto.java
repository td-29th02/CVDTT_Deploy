package com.cafe.dtos.topping;

import java.math.BigDecimal;
import java.util.List;

public class DrinkWithToppingsDto {
    private Integer drinkId;
    private String drinkName;
    private BigDecimal basePrice;
    private List<ToppingResponseDto> toppings;
    private BigDecimal totalPrice;
    
    // Constructors
    public DrinkWithToppingsDto() {}
    
    public DrinkWithToppingsDto(Integer drinkId, String drinkName, BigDecimal basePrice, 
                               List<ToppingResponseDto> toppings, BigDecimal totalPrice) {
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.basePrice = basePrice;
        this.toppings = toppings;
        this.totalPrice = totalPrice;
    }
    
    // Getters and Setters
    public Integer getDrinkId() { return drinkId; }
    public void setDrinkId(Integer drinkId) { this.drinkId = drinkId; }
    
    public String getDrinkName() { return drinkName; }
    public void setDrinkName(String drinkName) { this.drinkName = drinkName; }
    
    public BigDecimal getBasePrice() { return basePrice; }
    public void setBasePrice(BigDecimal basePrice) { this.basePrice = basePrice; }
    
    public List<ToppingResponseDto> getToppings() { return toppings; }
    public void setToppings(List<ToppingResponseDto> toppings) { this.toppings = toppings; }
    
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}

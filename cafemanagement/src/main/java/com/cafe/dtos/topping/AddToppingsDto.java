package com.cafe.dtos.topping;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddToppingsDto {
    @NotNull(message = "Drink ID is required")
    private Integer drinkId;
    
    @NotEmpty(message = "At least one topping is required")
    private List<Integer> toppingIds;
    
    // Constructors
    public AddToppingsDto() {}
    
    // Getters and Setters
    public Integer getDrinkId() { return drinkId; }
    public void setDrinkId(Integer drinkId) { this.drinkId = drinkId; }
    
    public List<Integer> getToppingIds() { return toppingIds; }
    public void setToppingIds(List<Integer> toppingIds) { this.toppingIds = toppingIds; }
}

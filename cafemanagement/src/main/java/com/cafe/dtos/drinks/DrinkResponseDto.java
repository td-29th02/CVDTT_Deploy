package com.cafe.dtos.drinks;

import java.math.BigDecimal;

import com.cafe.entities.Drink;

public class DrinkResponseDto {
    private Integer id;
    private String name;
    private String type;
    private BigDecimal basePrice;
    private String description;
    private String brewingMethod;
    private String origin;
    private Integer caffeineLevel;
    private Boolean isActive;
    
    // Constructors
    public DrinkResponseDto() {}
    
    public DrinkResponseDto(Drink drink) {
        this.id = drink.getId();
        this.name = drink.getName();
        this.type = drink.getType();
        this.basePrice = drink.getBasePrice();
        this.description = drink.getDescription();
        this.brewingMethod = drink.getBrewingMethod();
        this.origin = drink.getOrigin();
        this.caffeineLevel = drink.getCaffeineLevel();
        this.isActive = drink.getIsActive();
    }
    
    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public BigDecimal getBasePrice() { return basePrice; }
    public void setBasePrice(BigDecimal basePrice) { this.basePrice = basePrice; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getBrewingMethod() { return brewingMethod; }
    public void setBrewingMethod(String brewingMethod) { this.brewingMethod = brewingMethod; }
    
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    
    public Integer getCaffeineLevel() { return caffeineLevel; }
    public void setCaffeineLevel(Integer caffeineLevel) { this.caffeineLevel = caffeineLevel; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
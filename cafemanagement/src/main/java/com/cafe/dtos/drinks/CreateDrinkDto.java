package com.cafe.dtos.drinks;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateDrinkDto {
    @NotBlank(message = "Drink name is required")
    private String name;
    
    @NotBlank(message = "Drink type is required")
    private String type; // Coffee or Tea
    
    @NotNull(message = "Base price is required")
    @Positive(message = "Base price must be greater than 0")
    private BigDecimal basePrice;
    
    private String description;
    
    // Thêm các trường mới
    private String brewingMethod; // Espresso, Drip, French Press cho Coffee
    private String origin; // Origin của Coffee hoặc Tea
    private Integer caffeineLevel; // 1-5 scale
    
    @NotNull(message = "Active status is required")
    private Boolean isActive = true; // Cho phép người dùng chọn trạng thái
    
    // Constructors
    public CreateDrinkDto() {}
    
    // Getters and Setters
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
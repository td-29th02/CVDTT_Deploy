package com.cafe.dtos.topping;

import java.math.BigDecimal;

import com.cafe.entities.Topping;

public class ToppingResponseDto {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Boolean isActive;
    
    // Constructors
    public ToppingResponseDto() {}
    
    public ToppingResponseDto(Topping topping) {
        this.id = topping.getId();
        this.name = topping.getName();
        this.price = topping.getPrice();
        this.isActive = topping.getIsActive();
    }
    
    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
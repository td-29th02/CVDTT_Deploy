package com.cafe.services.patterns.decorator;

import java.math.BigDecimal;

public interface DrinkItem {
    String getDescription();
    BigDecimal getPrice();
    Integer getDrinkId();
}

package com.springboot.project.airBnbApp.strategy;

import com.springboot.project.airBnbApp.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);
}

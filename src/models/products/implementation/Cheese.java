package models.products.implementation;

import models.products.ExpirableProduct;
import models.products.Shippable;

import java.time.LocalDate;

public class Cheese extends ExpirableProduct implements Shippable {
    private final double weightPerUnit;

    public Cheese(String productName, double unitPrice, int stockQuantity, 
                  LocalDate bestBeforeDate, double weightInKg) {
        super(productName, unitPrice, stockQuantity, bestBeforeDate);
        this.weightPerUnit = weightInKg;
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeight() {
        return weightPerUnit;
    }
}
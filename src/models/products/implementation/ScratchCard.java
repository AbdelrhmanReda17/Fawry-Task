package models.products.implementation;

import models.products.NonExpirableProduct;

public class ScratchCard extends NonExpirableProduct {
    public ScratchCard(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean requiresShipping() {
        return false;
    }

    @Override
    public double getWeight() {
        return 0.0;
    }
}
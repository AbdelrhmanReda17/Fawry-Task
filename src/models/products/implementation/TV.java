package models.products.implementation;


import models.products.NonExpirableProduct;
import models.products.Shippable;

public class TV extends NonExpirableProduct implements Shippable {
    private final double weight;

    public TV(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
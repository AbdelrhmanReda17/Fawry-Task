package models.products;

import java.time.LocalDate;

public abstract class ExpirableProduct extends Product {
    protected LocalDate expirationDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiry) {
        super(name, price, quantity);
        this.expirationDate = expiry;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
}
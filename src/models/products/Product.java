package models.products;

public abstract class Product {
    protected String productName;
    protected double unitPrice;
    protected int stockQuantity;

    public Product(String name, double price, int availableQuantity) {
        this.productName = name;
        this.unitPrice = price;
        this.stockQuantity = availableQuantity;
    }

    public String getName() {
        return productName;
    }

    public double getPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return stockQuantity;
    }

    public void reduceQuantity(int amountToReduce) {
        this.stockQuantity -= amountToReduce;
    }

    public boolean isAvailable(int requestedQuantity) {
        return stockQuantity >= requestedQuantity;
    }

    public abstract boolean isExpired();
    public abstract boolean requiresShipping();
    public abstract double getWeight();
}

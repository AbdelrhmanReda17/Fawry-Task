package models.cart;

import models.products.Product;

public class CartItem {
    private Product productItem;
    private int orderedQuantity;

    public CartItem(Product product, int qty) {
        this.productItem = product;
        this.orderedQuantity = qty;
    }

    public Product getProduct() {
        return productItem;
    }

    public int getQuantity() {
        return orderedQuantity;
    }

    public double getTotalPrice() {
        return productItem.getPrice() * orderedQuantity;
    }
}
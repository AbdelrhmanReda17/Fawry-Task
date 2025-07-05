package models.cart;

import models.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void add(Product productToAdd, int requestedQuantity) throws IllegalArgumentException {
        if (productToAdd == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (requestedQuantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        if (!productToAdd.isAvailable(requestedQuantity)) {
            throw new IllegalArgumentException("Insufficient stock for " + productToAdd.getName());
        }

        cartItems.add(new CartItem(productToAdd, requestedQuantity));
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(cartItems);
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public double getSubtotal() {
        return cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public void clear() {
        cartItems.clear();
    }
}
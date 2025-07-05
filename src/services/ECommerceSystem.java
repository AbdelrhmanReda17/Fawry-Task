package services;

import models.cart.Cart;
import models.cart.CartItem;
import models.customer.Customer;
import models.products.Product;
import models.shipping.ShippingItem;

import java.util.ArrayList;
import java.util.List;

public class ECommerceSystem {
    private final ShippingService shippingCoordinator;

    public ECommerceSystem() {
        this.shippingCoordinator = new ShippingService();
    }

    public void checkout(Customer buyer, Cart customerCart) {
        try {
            validateCheckout(buyer, customerCart);

            List<ShippingItem> itemsToShip = collectShippingItems(customerCart);
            double orderSubtotal = customerCart.getSubtotal();
            double deliveryFee = shippingCoordinator.calculateShippingFee(itemsToShip);
            double finalAmount = orderSubtotal + deliveryFee;

            buyer.deductBalance(finalAmount);
            updateProductQuantities(customerCart);
            shippingCoordinator.processShipment(itemsToShip);
            printOrderReceipt(customerCart, orderSubtotal, deliveryFee, finalAmount, buyer.getBalance());
            customerCart.clear();

        } catch (Exception orderException) {
            System.err.println("Checkout failed: " + orderException.getMessage());
        }
    }

    private void validateCheckout(Customer buyer, Cart customerCart) throws IllegalStateException {
        if (customerCart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        for (CartItem cartItem : customerCart.getItems()) {
            Product productToCheck = cartItem.getProduct();

            if (productToCheck.isExpired()) {
                throw new IllegalStateException("Product " + productToCheck.getName() + " is expired");
            }

            if (!productToCheck.isAvailable(cartItem.getQuantity())) {
                throw new IllegalStateException("Product " + productToCheck.getName() + " is out of stock");
            }
        }

        double orderSubtotal = customerCart.getSubtotal();
        List<ShippingItem> itemsToShip = collectShippingItems(customerCart);
        double deliveryFee = shippingCoordinator.calculateShippingFee(itemsToShip);
        double finalAmount = orderSubtotal + deliveryFee;

        if (buyer.hasInsufficientBalance(finalAmount)) {
            throw new IllegalStateException("Customer's balance is insufficient");
        }
    }

    private List<ShippingItem> collectShippingItems(Cart customerCart) {
        List<ShippingItem> itemsRequiringShipping = new ArrayList<>();

        for (CartItem cartItem : customerCart.getItems()) {
            Product productToCheck = cartItem.getProduct();
            if (productToCheck.requiresShipping()) {
                itemsRequiringShipping.add(new ShippingItem(
                        productToCheck.getName(),
                        productToCheck.getWeight(),
                        cartItem.getQuantity()
                ));
            }
        }

        return itemsRequiringShipping;
    }

    private void updateProductQuantities(Cart customerCart) {
        for (CartItem cartItem : customerCart.getItems()) {
            cartItem.getProduct().reduceQuantity(cartItem.getQuantity());
        }
    }

    private void printOrderReceipt(Cart customerCart, double orderSubtotal, double deliveryFee, double finalAmount, double customerRemainingBalance) {
        System.out.println("** Checkout receipt **");

        for (CartItem cartItem : customerCart.getItems()) {
            System.out.printf("%dx %s %.0f%n",
                    cartItem.getQuantity(),
                    cartItem.getProduct().getName(),
                    cartItem.getTotalPrice());
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", orderSubtotal);
        System.out.printf("Shipping %.0f%n", deliveryFee);
        System.out.printf("Amount %.0f%n", finalAmount);
        System.out.printf("Customer balance after payment: %.0f%n", customerRemainingBalance);
        System.out.println("END.");
    }
}
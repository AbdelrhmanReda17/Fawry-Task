import models.cart.Cart;
import models.customer.Customer;
import models.products.implementation.Biscuits;
import models.products.implementation.Cheese;
import models.products.implementation.ScratchCard;
import models.products.implementation.TV;
import services.ECommerceSystem;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Cheese dairyCheese = new Cheese("Premium Cheddar Cheese", 100.0, 10,
                                      LocalDate.now().plusDays(7), 0.2);
        TV samsungTV = new TV("Samsung 55inch Smart TV", 500.0, 5, 15.0);
        ScratchCard mobilePrepaidCard = new ScratchCard("Mobile Scratch Card", 25.0, 100);
        Biscuits digestiveBiscuits = new Biscuits("McVitie's Digestives", 150.0, 8,
                                                LocalDate.now().plusDays(14), 0.7);

        Customer johnDoe = new Customer("John Doe", 2000);

        Cart shoppingCart = new Cart();

        try {
            shoppingCart.add(dairyCheese, 2);
            shoppingCart.add(samsungTV, 1);
            shoppingCart.add(mobilePrepaidCard, 1);
            shoppingCart.add(digestiveBiscuits, 1);

            ECommerceSystem ecommerceSystem = new ECommerceSystem();
//            ecommerceSystem.checkout(johnDoe, shoppingCart);

        } catch (Exception ex) {
            System.err.println("Shopping error occurred: " + ex.getMessage());
        }

        System.out.println("\n=== Testing Edge Cases ===");


        // Empty Cart Checkout
        Cart emptyShoppingCart = new Cart();
        ECommerceSystem testSystem = new ECommerceSystem();
        testSystem.checkout(johnDoe, emptyShoppingCart);
        Cart expensiveItemsCart = new Cart();

        // insufficient stock for expensive items
        Customer customer = new Customer("Poor Customer", 10000.0);
        try {
            expensiveItemsCart.add(dairyCheese, 20);
            expensiveItemsCart.add(samsungTV, 1);
            expensiveItemsCart.add(mobilePrepaidCard, 1);
            expensiveItemsCart.add(digestiveBiscuits, 1);
            testSystem.checkout(customer, expensiveItemsCart);
        } catch (Exception ex) {
            System.err.println("Purchase attempt failed: " + ex.getMessage());
        }

        // Expired Product Checkout
        try {
            Cheese expiredCheese = new Cheese("Expired Cheese", 50.0, 5,
                                            LocalDate.now().minusDays(1), 0.1);

            Cart expiredCart = new Cart();
            expiredCart.add(expiredCheese, 1);
            testSystem.checkout(customer, expiredCart);
        } catch (Exception ex) {
            System.err.println("Purchase attempt failed: " + ex.getMessage());
        }



        try {
            Customer studentCustomer = new Customer("Poor Student", 10.0);
            expensiveItemsCart.add(samsungTV, 2);
            testSystem.checkout(studentCustomer, expensiveItemsCart);
        } catch (Exception ex) {
            System.err.println("Purchase attempt failed: " + ex.getMessage());
        }
    }
}
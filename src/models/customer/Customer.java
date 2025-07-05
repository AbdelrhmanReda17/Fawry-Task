package models.customer;

public class Customer {
    private final String customerName;
    private double accountBalance;

    public Customer(String name, double initialBalance) {
        this.customerName = name;
        this.accountBalance = initialBalance;
    }

    public String getName() {
        return customerName;
    }

    public double getBalance() {
        return accountBalance;
    }

    public void deductBalance(double amountToDeduct) {
        this.accountBalance -= amountToDeduct;
    }

    public boolean hasInsufficientBalance(double requiredAmount) {
        return accountBalance < requiredAmount;
    }
}
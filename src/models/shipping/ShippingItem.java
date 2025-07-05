package models.shipping;

public class ShippingItem {
    private final String itemName;
    private final double itemWeight;
    private final int itemQuantity;

    public ShippingItem(String name, double weight, int quantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        this.itemName = name.trim();
        this.itemWeight = weight;
        this.itemQuantity = quantity;
    }

    public String getName() {
        return itemName;
    }

    public double getWeight() {
        return itemWeight;
    }

    public int getQuantity() {
        return itemQuantity;
    }

    public double getTotalWeight() {
        return itemWeight * itemQuantity;
    }

    @Override
    public String toString() {
        return String.format("%dx %s (%.2fkg each, %.2fkg total)", 
                           itemQuantity, itemName, itemWeight, getTotalWeight());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        ShippingItem that = (ShippingItem) obj;
        return Double.compare(that.itemWeight, itemWeight) == 0 &&
               itemName.equals(that.itemName);
    }

    @Override
    public int hashCode() {
        int result = itemName.hashCode();
        long temp = Double.doubleToLongBits(itemWeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

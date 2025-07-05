package services;

import models.shipping.ShippingItem;
import java.util.List;

public class ShippingService {
    private static final double COST_PER_KILOGRAM = 10.0;
    private static final double MINIMUM_SHIPPING_CHARGE = 20.0;
    
    public void processShipment(List<ShippingItem> packageItems) {
        if (packageItems.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        double totalPackageWeight = 0.0;

        for (ShippingItem packageItem : packageItems) {
            double itemTotalWeight = packageItem.getTotalWeight();
            totalPackageWeight += itemTotalWeight;
            System.out.printf("%dx %s %.0fg%n",
                    packageItem.getQuantity(),
                    packageItem.getName(),
                    itemTotalWeight * 1000);
        }

        System.out.printf("Total package weight %.1fkg%n", totalPackageWeight);
    }

    public double calculateShippingFee(List<ShippingItem> packageItems) {
        if (packageItems.isEmpty()) {
            return 0.0;
        }

        double totalPackageWeight = packageItems.stream()
                .mapToDouble(ShippingItem::getTotalWeight)
                .sum();

        return MINIMUM_SHIPPING_CHARGE + (totalPackageWeight * COST_PER_KILOGRAM);
    }
}
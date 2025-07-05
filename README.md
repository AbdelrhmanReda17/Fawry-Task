# Fawry Task

## Sample Output

### Successful Purchase
![normal_case](https://github.com/user-attachments/assets/51bf4a30-c0f2-4d5e-a4b5-45e265072b3f)
```
** Shipment notice **
2x Premium Cheddar Cheese        400g
1x Samsung 55inch Smart TV       15000g
1x McVitie's Digestives         700g
Total package weight 16.1kg
** Checkout receipt **
2x Premium Cheddar Cheese        200
1x Samsung 55inch Smart TV       500
1x Mobile Scratch Card           25
1x McVitie's Digestives         150
----------------------
Subtotal                         875
Shipping                         181
Amount                          1056
Customer balance after payment: 944
END.
```

### Error Cases
![edge_cases](https://github.com/user-attachments/assets/83ac0601-ea11-4268-89d9-f6c262447805)

**Empty Cart:**
```
Checkout failed: Cart is empty
```

**Insufficient Balance:**
```
Checkout failed: Customer's balance is insufficient
```

**Out of Stock:**
```
Shopping error occurred: Insufficient stock for Samsung 55inch Smart TV
```

**Expired Product:**
```
Checkout failed: Product Premium Cheddar Cheese is expired
```

## Project Structure

```
src/
├── Main.java
├── models/
│   ├── cart/
│   ├── customer/
│   ├── products/
│   │   └── implementation/
│   └── shipping/
└── services/
```

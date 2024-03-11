package com.vyborova.documentsapp2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@Component
public class Order {
    private Map<Item, Integer> items;
    private double totalAmount;
    private Date orderDate;
    private Invoice invoice;
    private PaymentDocument paymentDocument;
    private Waybill waybill;

    public Order(Map<Item, Integer> items) {
        this.items = items;
        this.orderDate = new Date();
        for (Item item : items.keySet()) {
            totalAmount += item.getPrice() * items.get(item);
        }

    }

    public Order() {
        System.out.println("Order created");
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", totalAmount=" + totalAmount +
                ", orderDate=" + orderDate +
                ", invoice=" + invoice +
                '}';
    }
}

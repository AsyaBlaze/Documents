package com.vyborova.documentsapp2;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CreationController {
    private static List<Order> orders = new ArrayList<>();

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean wantToStay = true;
        while (wantToStay) {
            System.out.println("Выберите действие:");
            System.out.println("1. Создать заказ");
            System.out.println("2. Создать счет");
            System.out.println("3. Создать документ оплаты");
            System.out.println("4. Создать накладную");
            System.out.println("5. Вывести все заказы");
            System.out.println("6. Вывести все счета");
            System.out.println("7. Вывести все документы оплаты");
            System.out.println("8. Вывести все накладные");
            System.out.println("0. Выйти");

            int chosen = scanner.nextInt();
            Order order;
            switch (chosen) {
                case 1:
                    createOrder();
                    break;
                case 2:
                    order = forWhichOrder();
                    if (order == null) {
                        break;
                    }
                    createInvoice(order);
                    break;
                case 3:
                    order = forWhichOrder();
                    if (order == null) {
                        break;
                    }
                    createPaymentDocument(order);
                    break;
                case 4:
                    order = forWhichOrder();
                    if (order == null) {
                        break;
                    }
                    createWaybill(order);
                    break;
                case 5:
                    printOrders();
                    break;
                case 6:
                    printInvoices();
                    break;
                case 7:
                    printPaymentDocuments();
                    break;
                case 8:
                    printWaybills();
                    break;
                case 0:
                    wantToStay = false;
                    break;
            }
        }
    }

    private static void printWaybills() {
        orders.forEach(order -> System.out.println(order.getWaybill()));
    }

    private static void createWaybill(Order order) {
        Scanner scanner = new Scanner(System.in);
        Waybill waybill = new Waybill();
        waybill.setDateOfCreation(new Date());
        System.out.println("Впишите адресс доставки: ");
        waybill.setShippingAddress(scanner.next());
        order.setWaybill(waybill);
    }

    private static void printPaymentDocuments() {
        orders.forEach(order -> System.out.println(order.getPaymentDocument()));
    }

    private static void createPaymentDocument(Order order) {
        PaymentDocument paymentDocument = new PaymentDocument();
        paymentDocument.setPaymentDate(new Date());
        paymentDocument.setInvoice(order.getInvoice());
        order.setPaymentDocument(paymentDocument);
    }

    private static void printInvoices() {
        orders.forEach(order -> System.out.println(order.getInvoice()));
    }

    private static Order forWhichOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Для какого заказа вы хотите создать счет? Введите номер заказа:  AV");
        int orderIndex = scanner.nextInt();
        if (orderIndex <= 0 || orderIndex > orders.size()) {
            System.out.println("Введенный номер заказа не существует");
            return null;
        }
        return orders.get(orderIndex - 1);
    }


    private static void createInvoice(Order order) {
        Scanner scanner = new Scanner(System.in);
        Invoice invoice = new Invoice();
        invoice.setDate(new Date());
        invoice.setTotalAmount(order.getTotalAmount());
        System.out.println("Введите номер кредитной карточки: ");
        String creditCardNumber = scanner.next().trim();
        if (creditCardNumber.matches("^\\d{16}$")) {
            throw new IllegalArgumentException("Номер карточки должен содержать 16 цифр");
        }
        invoice.setCreditCardNumber(creditCardNumber);
        order.setInvoice(invoice);
    }

    private static void printOrders() {
        orders.forEach(System.out::println);
    }

    private static void createOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Как много уникальных товаров вы покупаете? ");
        int n = scanner.nextInt();
        if (n < 1) {
            System.out.println("Чтобы оформить заказ вы должны заказать как минимум один товар");
            return;
        }
        Map<Item,Integer> itemsList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Введите товар ____(наименование) ____(цена) ___(количество): ");
            itemsList.put(new Item(scanner.next(), Double.parseDouble(scanner.next())), Integer.parseInt(scanner.next()));
        }
        Order order = new Order(itemsList);
        orders.add(order);
        System.out.println("Заказ с номером AV" + orders.size() + " был создан");
        System.out.println("Вы бы хотели создать счёт для него? д/н");
        String answer = scanner.next();
        if (answer.equals("д")) {
            createInvoice(order);
            System.out.println("Вы бы хотели добавить документ об оплате к этому заказу? д/н");
            answer = scanner.next();
            if (answer.equals("д")) {
                createPaymentDocument(order);
            }
        }
    }
}

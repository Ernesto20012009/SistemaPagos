package com.payments;

import com.payments.entities.CreditCardPayment;
import com.payments.entities.Payment;

public class PaymentsApp {
    public static void main(String[] args) {
    Payment pag1 = new CreditCardPayment(503.54,"08/10/2026",1500);
    Payment pag2 = new CreditCardPayment(503.54,"",200);

    //tarjeta de credito
        pag2.processPayment();


        System.out.println(pag1);
        System.out.println(pag2);
        // imprimir tarjeta de credito

    }
}

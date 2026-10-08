package com.payments;

import com.payments.entities.CreditCardPayment;
import com.payments.entities.Payment;

public class PaymentsApp {
    public static void main(String[] args) {
    Payment pag1 = new CreditCardPayment("aprovved",503.54,"08/10/2026",1500);
    Payment pag2 = new CreditCardPayment("",503.54,"",2000);

    //tarjeta de credito
        pag1.processPayment();


        System.out.println(pag1);
        System.out.println(pag2);
        // imprimir tarjeta de credito

    }
}

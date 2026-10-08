package com.payments;

import com.payments.entities.CreditCardPayment;
import com.payments.entities.Payment;
import com.payments.entities.PaypalPayment;

public class PaymentsApp {
    public static void main(String[] args) {
    Payment pag1 = new CreditCardPayment(503.54,"08/10/2026",1500,"123456","Pedro");
    Payment pag2 = new CreditCardPayment(503.54,"",200,"1234567890123456"," ALGO");
    Payment pag3 = new PaypalPayment(503.54,"",2000,"neto@gmail.com");
    //tarjeta de credito
        pag2.processPayment();


        System.out.println("PAGO 1: "+pag1);
        System.out.println("PAGO 2: "+pag2);
        System.out.println("PAGO 3: "+pag3);
        // imprimir tarjeta de credito

    }
}

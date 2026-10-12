package com.payments;

import com.payments.entities.CreditCardPayment;
import com.payments.entities.Payment;
import com.payments.entities.PaymentManager;
import com.payments.entities.PaypalPayment;
import com.payments.exceptions.InsufficientFundsException;
import com.payments.exceptions.InvalidPaymentException;

import java.util.ArrayList;

public class PaymentsApp {
    public static void main(String[] args) {
        PaymentManager manager = new PaymentManager();
        //crear pago creditcard
        CreditCardPayment pago1 = new CreditCardPayment(500000003.54,"08/10/2026",1500,"123456","Pedro");
        manager.addPayment(pago1);
        manager.showPayments();
        manager.findPaymentById(1);

        System.out.println("to string: ");
        // insufficient
        try {
            ((CreditCardPayment)pago1).processPayment();
        }catch (InsufficientFundsException e){
            System.out.println("ERROR: NO SE ECONTRO NADA XD "+e.getMessage());
        }// fin catch


        //invalidpayment
        try {
            ((CreditCardPayment)pago1).processPayment();
        }catch (InvalidPaymentException e){
            System.out.println("ERROR: NO SE ECONTRARON PAGOS :"+e.getMessage());
        }// fin catch

    /*Payment pag1 = new CreditCardPayment(503.54,"08/10/2026",1500,"123456","Pedro");
    Payment pag2 = new CreditCardPayment(503.54,"",200,"1234567890123456"," ALGO");
    Payment pag3 = new PaypalPayment(503.54,"",2000,"neto@gmail.com");
    //tarjeta de credito
        pag2.processPayment();



        System.out.println("PAGO 1: "+pag1);
        System.out.println("PAGO 2: "+pag2);
        System.out.println("PAGO 3: "+pag3);
        // imprimir tarjeta de credito

     */

    }
}

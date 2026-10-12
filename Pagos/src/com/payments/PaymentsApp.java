package com.payments;

import com.payments.entities.*;
import com.payments.exceptions.InsufficientFundsException;
import com.payments.exceptions.InvalidPaymentException;
import com.payments.interfaces.Refundable;

public class PaymentsApp {
    public static void main(String[] args) {
        PaymentManager manager = new PaymentManager();

        // Caso 1: pago exitoso con tarjeta
        CreditCardPayment pago1 = new CreditCardPayment(
                1000, "08/10/2026", 5000, "1234567890123456", "Pedro");
        manager.addPayment(pago1);

        // Caso 2: pago rechazado (limite insuficiente) -> InsufficientFundsException
        CreditCardPayment pago2 = new CreditCardPayment(
                6000, "08/10/2026", 5000, "1234567890123456", "Ana");
        manager.addPayment(pago2);

        // Caso 3: diferentes metodos
        PaypalPayment pago3 = new PaypalPayment(
                500, "09/10/2026", 2000, "neto@gmail.com");
        manager.addPayment(pago3);

        BankTransferPayment pago4 = new BankTransferPayment(
                300, "10/10/2026", "1234567890", "BBVA", 5000);
        manager.addPayment(pago4);

        // Caso 4: procesar pagos
        System.out.println("\n--- Procesando pagos ---");
        try {
            pago1.processPayment();
        } catch (InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            pago2.processPayment();
        } catch (InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            pago3.processPayment();
        } catch (InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            pago4.processPayment();
        } catch (InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // Caso 5: InvalidPaymentException
        System.out.println("\n--- Pago con datos invalidos ---");
        try {
            CreditCardPayment invalido = new CreditCardPayment(
                    100, "11/10/2026", 1000, "123", "Luis");
            invalido.processPayment();
        } catch (InvalidPaymentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // Caso 6: reembolso con referencia Refundable
        System.out.println("\n--- Reembolso ---");
        Refundable reembolsable = pago1; // pago1 es CreditCardPayment
        reembolsable.refund();

        // Mostrar todos los pagos y total
        System.out.println("\n--- Pagos registrados ---");
        manager.showPayments();
        System.out.println("Total de pagos: " + manager.getTotalPayments());

        // Busqueda por ID
        System.out.println("\n--- Busqueda por ID ---");
        try {
            Payment encontrado = manager.findPaymentById(1);
            System.out.println("Encontrado: " + encontrado);
        } catch (InvalidPaymentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // Busqueda inexistente
        try {
            manager.findPaymentById(999);
        } catch (InvalidPaymentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
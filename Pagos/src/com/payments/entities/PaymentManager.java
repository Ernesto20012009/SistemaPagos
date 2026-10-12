package com.payments.entities;

import com.payments.exceptions.InvalidPaymentException;
import java.util.ArrayList;

public class PaymentManager {

    private ArrayList<Payment> payments;

    public PaymentManager(){
        this.payments = new ArrayList<>();
    }

    public void addPayment(Payment payment){
        if (payment == null) {
            throw new InvalidPaymentException("No se puede registrar un pago nulo");
        }
        this.payments.add(payment);
        System.out.println("Pago registrado correctamente... " + payment);
    }

    public void showPayments(){
        if (payments.isEmpty()) {
            System.out.println("No hay pagos registrados");
            return;
        }
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    public Payment findPaymentById(long id){
        for (Payment payment : payments) {
            if (payment.getId() == id) {
                return payment;
            }
        }
        throw new InvalidPaymentException("No existe un pago con ID: " + id);
    }

    public int getTotalPayments(){
        return payments.size();
    }
}
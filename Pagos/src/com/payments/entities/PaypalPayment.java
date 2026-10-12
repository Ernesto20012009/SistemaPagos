package com.payments.entities;

import com.payments.interfaces.Refundable;
import com.payments.exceptions.InsufficientFundsException;
import com.payments.exceptions.InvalidPaymentException;

public class PaypalPayment extends Payment implements Refundable {
    private double balance;
    private String email;

    public PaypalPayment(double amount, String date, double balance, String email){
        super(amount, date);
        this.balance = (balance < 0) ? 1.0 : balance;
        this.email = (email == null || email.isBlank()) ? "ERROR> INGRESA UN CORREO!" : email.trim();
    }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public void validateData() {
        if (email == null || email.isBlank() || email.startsWith("ERROR") || !email.contains("@")) {
            throw new InvalidPaymentException("Correo invalido");
        }
        if (getAmount() <= 0) {
            throw new InvalidPaymentException("Monto invalido");
        }
    }

    @Override
    public void processPayment() {
        validateData();
        if (getAmount() > balance) {
            setStatus("REJECTED");
            throw new InsufficientFundsException(
                    "Saldo insuficiente. Saldo: " + balance + ", Monto: " + getAmount());
        }
        setStatus("APPROVED");
        System.out.println("El estado del pago es: " + getStatus());
    }

    @Override
    public void refund() {
        if ("APPROVED".equals(getStatus())) {
            setStatus("REJECTED");
            System.out.println("Reembolso realizado a " + email);
        } else {
            System.out.println("No se puede reembolsar un pago no aprobado");
        }
    }

    @Override
    public String toString() {
        return "PaypalPayment{balance=" + balance + ", email='" + email + "'} " + super.toString();
    }
}
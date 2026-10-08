package com.payments.entities;

import com.payments.interfaces.Refundable;
                                            //interfaz
public class PaypalPayment extends Payment implements Refundable {
    private double balance;// saldo disponible
    private  String email;

// INICIO CONSTRUCTOR
    public PaypalPayment (double amount, String date, double balance, String email){
        super(amount, date);
        this.balance = (balance < 0) ? 1.0 : balance;
        this.email = (email.isBlank())? "ERROR> INGRESA UN CORREO!": email.trim();
    }// FIN CONSTRUCTOR

    public double getBalance() {
        return balance;
    }//getter

    public void setBalance(double balance) {
        this.balance = balance;
    }//setter

    @Override
    public void processPayment() {
        if (getAmount() > balance){
            setStatus("REJECTED");
        } else {
            setStatus("APPROVED");
        }//fin else
        System.out.println("El estado del pago es: " + getStatus());
    }//processPayment

    @Override
    public void validateData() {
        
    }

    // toString

    @Override
    public String toString() {
        return "PaypalPayment{" +
                "balance=" + balance +
                ", email='" + email + '\'' +
                "} " + super.toString();
    }

// to string PaypalPayment
    @Override
    public void refund() {

    }// fin interfaz refund
}//class PypalPayments

package com.payments.entities;

public class PaypalPayment extends Payment {
    private double balance;

    public PaypalPayment (double amount, String date, double balance){
        super(amount, date);
        this.balance = (balance < 0) ? 1.0 : balance;
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
}//class PypalPayments

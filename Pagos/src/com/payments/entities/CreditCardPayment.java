package com.payments.entities;

public class CreditCardPayment extends Payment {
    private double paymentLimit ;


    //CONSTRUCTOR TARJETA CREDITO
    public CreditCardPayment (String status, double amount, String date, double paymentLimit){
        super(status,amount,date);
        this.paymentLimit = (paymentLimit < 0) ? 1.0 : paymentLimit;
    }// FIN CONSTRUCTOR

    // getter
    public double getPaymentLimit(){
        return  paymentLimit;
    }// fin getter

    // setter
    public void setPaymentLimit(double paymentLimit) {
        this.paymentLimit = (paymentLimit < 0) ? 1.0 : paymentLimit;
    }// fin setter

    @Override
    public void processPayment() {
    if(getAmount()>paymentLimit){
        setStatus("REJECTED");
        System.out.println("REJECTED");
    }else{
        setStatus("APPROVED");
    }// fin else

    }// OVERRIDE

    }// fin processPayment

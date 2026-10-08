package com.payments.entities;

public class CreditCardPayment extends Payment {
    private double paymentLimit ;


    //CONSTRUCTOR TARJETA CREDITO
    public CreditCardPayment (double amount, String date, double paymentLimit){
        super(amount,date);
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
    }else{
        setStatus("APPROVED");
    }// fin else
        System.out.println("El estado del pago es: " + getStatus());
    }// OVERRIDE

    @Override
    public void validateData() {

    }

}// fin processPayment

package com.payments.entities;

import com.payments.interfaces.Refundable;

public class CreditCardPayment extends Payment implements Refundable {
    private double paymentLimit ;
    private String cardNum;
    private String holderName;


    //CONSTRUCTOR TARJETA CREDITO
    public CreditCardPayment (double amount, String date, double paymentLimit, String cardNum, String holderName){
        super(amount,date);
        this.paymentLimit = (paymentLimit < 0) ? 1.0 : paymentLimit;
        this.cardNum = (cardNum.length() !=  16) ? "ERROR:Ingresa un numero de 16 digitos" : cardNum;// recorrer caracteres
        this.holderName = (holderName.isBlank())? "ERROR: INGRESA EL NOMBRE DEL TITULAR": holderName.toUpperCase().trim();
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
    // TO STRING

    @Override
    public String toString() {
        return "CreditCardPayment{" +
                "paymentLimit=" + paymentLimit +
                ", cardNum='" + cardNum + '\'' +
                ", holderName='" + holderName + '\'' +
                "} " + super.toString();
    }
// refund metodo con implements
    @Override
    public void refund() {

    }
}// fin processPayment

package com.payments.entities;

import com.payments.interfaces.Refundable;
import com.payments.exceptions.InsufficientFundsException;
import com.payments.exceptions.InvalidPaymentException;

public class CreditCardPayment extends Payment implements Refundable {
    private double paymentLimit;
    private String cardNum;
    private String holderName;

    public CreditCardPayment(double amount, String date, double paymentLimit, String cardNum, String holderName){
        super(amount, date);
        this.paymentLimit = (paymentLimit < 0) ? 1.0 : paymentLimit;
        this.cardNum = (cardNum == null || cardNum.length() != 16)
                ? "ERROR: Ingresa un numero de 16 digitos" : cardNum;
        this.holderName = (holderName == null || holderName.isBlank())
                ? "ERROR: INGRESA EL NOMBRE DEL TITULAR" : holderName.toUpperCase().trim();
    }

    public double getPaymentLimit(){ return paymentLimit; }
    public void setPaymentLimit(double paymentLimit) {
        this.paymentLimit = (paymentLimit < 0) ? 1.0 : paymentLimit;
    }

    @Override
    public void validateData() {
        if (cardNum == null || cardNum.length() != 16 || !cardNum.matches("\\d+")) {
            throw new InvalidPaymentException("Numero de tarjeta invalido");
        }
        if (holderName == null || holderName.isBlank() || holderName.startsWith("ERROR")) {
            throw new InvalidPaymentException("Titular invalido");
        }
        if (getAmount() <= 0) {
            throw new InvalidPaymentException("Monto invalido");
        }
    }

    @Override
    public void processPayment() {
        validateData();
        if (getAmount() > paymentLimit) {
            setStatus("REJECTED");
            throw new InsufficientFundsException(
                    "Limite insuficiente. Limite: " + paymentLimit + ", Monto: " + getAmount());
        }
        setStatus("APPROVED");
        System.out.println("El estado del pago es: " + getStatus());
    }

    @Override
    public void refund() {
        if ("APPROVED".equals(getStatus())) {
            setStatus("REJECTED");
            System.out.println("Reembolso realizado para tarjeta " + cardNum);
        } else {
            System.out.println("No se puede reembolsar un pago no aprobado");
        }
    }

    @Override
    public String toString() {
        return "CreditCardPayment{paymentLimit=" + paymentLimit +
                ", cardNum='" + cardNum + "', holderName='" + holderName + "'} " + super.toString();
    }
}
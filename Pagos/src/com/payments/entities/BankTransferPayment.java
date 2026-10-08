package com.payments.entities;

public class BankTransferPayment extends Payment {
    private String accountNumber;
    private String bank;
    private int availableBalance;// saldo disponible

    //CONSTRUCTOR
    public BankTransferPayment(double amount, String date, String accountNumber, String bank, int availableBalance){
        super(amount,date);
        this.accountNumber = (accountNumber.length() != 10)? "ERROR> INGRESA UNA CUENTA DE 10 DIGITOS":accountNumber.trim();
        this.bank = (bank.isBlank())?"ERROR> INGRESE EL NOMBRE DE SU BANCO" : bank.trim().toUpperCase();
    }

    @Override
    public void processPayment() {

    }

    @Override
    public void validateData() {

    }
}

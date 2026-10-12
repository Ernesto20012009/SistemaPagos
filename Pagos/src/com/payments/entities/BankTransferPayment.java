package com.payments.entities;

import com.payments.exceptions.InsufficientFundsException;
import com.payments.exceptions.InvalidPaymentException;

public class BankTransferPayment extends Payment {
    private String accountNumber;
    private String bank;
    private int availableBalance;

    public BankTransferPayment(double amount, String date, String accountNumber, String bank, int availableBalance){
        super(amount, date);
        this.accountNumber = (accountNumber == null || accountNumber.length() != 10)
                ? "ERROR> INGRESA UNA CUENTA DE 10 DIGITOS" : accountNumber.trim();
        this.bank = (bank == null || bank.isBlank())
                ? "ERROR> INGRESE EL NOMBRE DE SU BANCO" : bank.trim().toUpperCase();
        this.availableBalance = (availableBalance < 0) ? 0 : availableBalance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getBank() { return bank; }
    public int getAvailableBalance() { return availableBalance; }
    public void setAvailableBalance(int availableBalance) { this.availableBalance = availableBalance; }

    @Override
    public void validateData() {
        if (accountNumber == null || accountNumber.length() != 10 || !accountNumber.matches("\\d+")) {
            throw new InvalidPaymentException("Cuenta invalida: deben ser 10 digitos numericos");
        }
        if (bank == null || bank.isBlank() || bank.startsWith("ERROR")) {
            throw new InvalidPaymentException("Banco invalido");
        }
        if (getAmount() <= 0) {
            throw new InvalidPaymentException("Monto invalido");
        }
    }

    @Override
    public void processPayment() {
        validateData();
        if (getAmount() > availableBalance) {
            setStatus("REJECTED");
            throw new InsufficientFundsException(
                    "Saldo insuficiente en banco " + bank + ". Saldo: " + availableBalance + ", Monto: " + getAmount());
        }
        setStatus("APPROVED");
        System.out.println("El estado del pago es: " + getStatus());
    }

    @Override
    public String toString() {
        return "BankTransferPayment{accountNumber='" + accountNumber +
                "', bank='" + bank + "', availableBalance=" + availableBalance + "} " + super.toString();
    }
}
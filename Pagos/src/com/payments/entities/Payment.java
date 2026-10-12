package com.payments.entities;

public abstract class Payment {
    private static long total;
    private long id;
    private String status;
    private double amount;
    private String date;

    public Payment(double amount, String date){
        Payment.total++;
        this.id = Payment.total;
        this.status = "PENDING";
        this.amount = (amount <= 0) ? 1.0 : amount;
        this.date = (date == null || date.isBlank()) ? "SIN FECHA" : date.toUpperCase();
    }

    // getters/setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) {
        this.status = (status == null || status.isBlank()) ? "PENDING" : status.toUpperCase();
    }

    public double getAmount() { return amount; }
    public void setAmount(double amount) {
        this.amount = (amount <= 0) ? 1.0 : amount;
    }

    public String getDate() { return date; }
    public void setDate(String date) {
        if (date == null || date.isBlank()) {
            System.out.println("ERROR. LA FECHA NO PUEDE IR VACIA");
        } else {
            this.date = date.toUpperCase();
        }
    }

    @Override
    public String toString() {
        return "Payment{id=" + id + ", status='" + status + "', amount=" + amount + ", date='" + date + "'}";
    }

    public abstract void processPayment();
    public abstract void validateData();
}
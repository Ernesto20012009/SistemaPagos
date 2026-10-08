package com.payments.entities;

public abstract class Payment {
    private static long total;
    private long id ;
    private String status;
    private double amount;
    private String date;


    // CONSTRUCTOR INICIO
    public Payment(double amount, String date){
        Payment.total++;
        this.id = Payment.total;
        this.status = "PENDING";
        this.amount = (amount <= 0) ? 1.0: amount;
        this.date = (date.isBlank()) ? "INGRESA UNA FECHA VALIDA": date.toUpperCase();
    }// fin constructor

    //getter setter

    /*
    public static long getTotal() {
        return total;
    }// fin getTotal

    public static void setTotal(long total) {
        Payment.total = total;
    }//fin setTotal

     */

    public long getId() {
        return id;
    }// fin getId

    public void setId(long id) {
        this.id = id;
    }// fin setId

    public String getStatus() {
        return status;
    }// fin getStatus

    public void setStatus(String status) {
        if(status.isBlank()){
            System.out.println("PENDING...");
        }else{
            this.status = status.toUpperCase();
        }// fin else
    }// fin setStatus

    public double getAmount() {
        return amount;
    }// fin getAmount

    public void setAmount(double amount) {
        this.amount = amount;
    }// fin setAmount

    public String getDate() {
        return date;
    }// fin getDate

    public void setDate(String date) {
        if(date.isBlank()){
            System.out.println("ERROR. LA FECHA NO PUDDE IR VACIA");
        }else{
            this.date = date.toUpperCase();
        }// fin else
    }// fin setDate

    // to string

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }

    // METODO processPayment
    public abstract void processPayment();

    //METODO validate data
    public abstract void validateData();




}// fin class Payment

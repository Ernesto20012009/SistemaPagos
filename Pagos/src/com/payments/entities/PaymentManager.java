package com.payments.entities;

import java.sql.SQLOutput;
import java.util.ArrayList;


public class PaymentManager extends Payment{
    //COLECCIONES
    private ArrayList <Payment> payments = new ArrayList<>() ;

    // CONSTRUCTOR DE LA CALSE
    public PaymentManager(){
        //super(id);
        this.payments = new ArrayList<>();
    }// fin constructor

    // funcion agregar
    public void addPayment(Payment payment){
        this.payments.add(payment);//agregar un pago a los pagos
        System.out.println("Pago registrado correctamente... "+payment);
    }// fin agregar

    // funcion buscar pago
    public void showPayments(){
        for(Payment payment: this.payments){
            if(payment.getId() == getId()) {
                System.out.println("ESTE ES TU ID: "+getId());
            }
        }
    }//fin buscar total pagos

    // buscar pago id
    public Payment findPaymentById(long id){
        for(Payment payment: this.payments){
            if(payment.getId()==id){
                System.out.println("ESta mal x2"+getId());
                return payment;
            }
        }// fin for
        System.out.println("No hay nada");
        return null;
    }// fin findPayment

    // mostar

    @Override
    public void processPayment() {

    }

    @Override
    public void validateData() {

    }
}// fin class

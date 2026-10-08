package com.payments.entities;

import java.util.ArrayList;

public class PaymentManager {
    //COLECCIONES
    private ArrayList <Payment> payments;

    // CONSTRUCTOR DE LA CALSE
    public PaymentManager(){
        this.payments = new ArrayList<>();
    }// fin constructor

    // funcion agregar
    public void addPayment(Payment payment){
        this.payments.add(payment);//agregar un pago a los pagos
    }// fin agregfar

    // funcion buscar pago
    public void showPayments(){
        for(Payment payment: this.payments){
            if(payment.getId() == id) {
                System.out.println(get);
            }
        }
    }//fin buscar
}// fin class

package com.payments;

import com.payments.entities.BankTransferPayment;
import com.payments.entities.CreditCardPayment;
import com.payments.entities.Payment;
import com.payments.entities.PaymentManager;
import com.payments.entities.PaypalPayment;
import com.payments.exceptions.InsufficientFundsException;
import com.payments.exceptions.InvalidPaymentException;
import com.payments.interfaces.Refundable;

import java.util.Scanner;

public class PaymentsApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PaymentManager manager = new PaymentManager();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> registrarPago();
                case 2 -> procesarPago();
                case 3 -> manager.showPayments();
                case 4 -> buscarPorId();
                case 5 -> System.out.println("Total de pagos registrados: "
                        + manager.getTotalPayments());
                case 6 -> reembolsarPago();
                case 7 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Error: Opción inválida. Intenta de nuevo.");
            }
            System.out.println();
        } while (opcion != 7);

        scanner.close();
    }

    // MENÚ PRINCIPAL
    private static void mostrarMenu() {
        System.out.println("════════════════════════════════════════════");
        System.out.println("          SISTEMA DE PAGOS  ");
        System.out.println("════════════════════════════════════════════");
        System.out.println("  1. Registrar un nuevo pago");
        System.out.println("  2. Procesar un pago por ID");
        System.out.println("  3. Mostrar todos los pagos");
        System.out.println("  4. Buscar pago por ID");
        System.out.println("  5. Ver total de pagos registrados");
        System.out.println("  6. Reembolsar un pago");
        System.out.println("  7. Salir");
        System.out.println("════════════════════════════════════════════");
    }

    // REGISTRAR PAGO

    private static void registrarPago() {
        System.out.println("\n--- REGISTRAR NUEVO PAGO ---");
        System.out.println("  Métodos disponibles:");
        System.out.println("    1. Tarjeta de crédito");
        System.out.println("    2. PayPal");
        System.out.println("    3. Transferencia bancaria");

        int tipo = leerEntero("Selecciona el método: ");

        try {
            double amount = leerDouble("Monto del pago: ");
            String date   = leerTexto("Fecha (dd/mm/aaaa): ");

            switch (tipo) {
                case 1 -> registrarTarjeta(amount, date);
                case 2 -> registrarPaypal(amount, date);
                case 3 -> registrarTransferencia(amount, date);
                default -> System.out.println("Error: Método inválido.");
            }
        } catch (Exception e) {
            System.out.println("Error: Error al registrar el pago: " + e.getMessage());
        }
    }

    private static void registrarTarjeta(double amount, String date) {
        double limite  = leerDouble("Límite de la tarjeta: ");
        String cardNum = leerTexto("Número de tarjeta (16 dígitos): ");
        String titular = leerTexto("Nombre del titular: ");

        // Constructor
        CreditCardPayment pago = new CreditCardPayment(amount, date, limite, cardNum, titular);
        manager.addPayment(pago);
    }

    private static void registrarPaypal(double amount, String date) {
        double balance = leerDouble("Saldo disponible en PayPal: ");
        String email   = leerTexto("Correo electrónico: ");

        // Constructor real: PaypalPayment(double, String, double, String)
        PaypalPayment pago = new PaypalPayment(amount, date, balance, email);
        manager.addPayment(pago);
    }

    private static void registrarTransferencia(double amount, String date) {
        String cuenta = leerTexto("Número de cuenta (10 dígitos): ");
        String banco  = leerTexto("Nombre del banco: ");
        int saldo     = (int) leerDouble("Saldo disponible en la cuenta: ");

        // Constructor real: BankTransferPayment(double, String, String, String, int)
        BankTransferPayment pago = new BankTransferPayment(amount, date, cuenta, banco, saldo);
        manager.addPayment(pago);
    }


    // PROCESAR PAGO

    private static void procesarPago() {
        System.out.println("\n--- PROCESAR PAGO ---");
        long id = leerEntero("ID del pago a procesar: ");

        try {
            Payment pago = manager.findPaymentById(id);
            pago.processPayment();
        } catch (InvalidPaymentException e) {
            System.out.println("Error " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Error: Fondos insuficientes: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(" Error inesperado: " + e.getMessage());
        }
    }


    // BUSCAR PAGO POR ID

    private static void buscarPorId() {
        System.out.println("\n--- BUSCAR PAGO POR ID ---");
        long id = leerEntero("ID a buscar: ");

        try {
            Payment pago = manager.findPaymentById(id);
            System.out.println(" Encontrado:");
            System.out.println("   " + pago);
        } catch (InvalidPaymentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // REEMBOLSAR PAGO

    private static void reembolsarPago() {
        System.out.println("\n--- REEMBOLSAR PAGO ---");
        long id = leerEntero("ID del pago a reembolsar: ");

        try {
            Payment pago = manager.findPaymentById(id);

            // Pattern matching (Java 16+): pago instanceof Refundable
            if (pago instanceof Refundable) {
                Refundable reembolsable = (Refundable) pago;
                reembolsable.refund();
            } else {
                System.out.println("Error: Este método de pago no admite reembolsos.");
            }
        } catch (InvalidPaymentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // UTILIDADES DE ENTRADA (privadas de esta clase)

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número entero.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String linea = scanner.nextLine().trim().replace(",", ".");
                return Double.parseDouble(linea);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número válido.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
}
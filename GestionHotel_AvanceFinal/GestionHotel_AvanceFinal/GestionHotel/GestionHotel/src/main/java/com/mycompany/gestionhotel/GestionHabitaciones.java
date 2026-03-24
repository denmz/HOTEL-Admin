package com.mycompany.gestionhotel;

/**
 * Proyecto programacion 
 * Daniela Munoz Valverde 
 * Dereck Madrigal Zamora
 * José Adrián Reyes Hernandez
 */
public class GestionHabitaciones {
    private static Habitacion[] habitaciones = new Habitacion[20];
    private static int contHabitaciones = 0;
    
    public GestionHabitaciones() {
        habitaciones[contHabitaciones++] = new Habitacion(1, TipoHabitacion.Estandar, 45000);
        habitaciones[contHabitaciones++] = new Habitacion(2, TipoHabitacion.Estandar, 45000);
        habitaciones[contHabitaciones++] = new Habitacion(3, TipoHabitacion.Estandar, 45000);
        habitaciones[contHabitaciones++] = new Habitacion(4, TipoHabitacion.Premium, 65000);
        habitaciones[contHabitaciones++] = new Habitacion(5, TipoHabitacion.Premium, 65000);
        habitaciones[contHabitaciones++] = new Habitacion(6, TipoHabitacion.Suite, 95000);
    }
    
    public static void mostrarHabitacionesDisponibles() {
        if(contHabitaciones == 0) {
            System.out.println("No hay habitaciones registradas");
            return;
        }
        
        System.out.println("\n--- Habitaciones Disponibles ---");
        
        boolean hayDisponibles = false;
        
        for(int i = 0; i < contHabitaciones; i++) {
            if(habitaciones[i].isDisponible()) {
                System.out.println("\nHabitacion #" + habitaciones[i].getNumero());
                System.out.println("Tipo: " + habitaciones[i].getTipo());
                System.out.println("Precio: " + habitaciones[i].getPrecio());
                System.out.println("Estado: Disponible");
                System.out.println("---");
                hayDisponibles = true;
            }
        }
        
        if(!hayDisponibles) {
            System.out.println("\nNo hay habitaciones disponibles");
        }
        System.out.println("");
    }
    
    public static void mostrarTodasHabitaciones() {
        if(contHabitaciones == 0) {
            System.out.println("No hay habitaciones registradas");
            return;
        }
        
        System.out.println("\n--- Todas las Habitaciones ---\n");
        
        for(int i = 0; i < contHabitaciones; i++) {
            System.out.println(habitaciones[i].toString());
            System.out.println("---");
        }
        System.out.println("");
    }
    
    public static Habitacion buscarHabitacion(int numero) {
        for(int i = 0; i < contHabitaciones; i++) {
            if(habitaciones[i].getNumero() == numero) {
                return habitaciones[i];
            }
        }
        return null;
    }
    
    public static boolean verificarDisponibilidad(int numero) {
        Habitacion hab = buscarHabitacion(numero);
        if(hab != null) {
            return hab.isDisponible();
        }
        return false;
    }
    
    public static void ocuparHabitacion(int numero) {
        Habitacion hab = buscarHabitacion(numero);
        if(hab != null) {
            hab.ocupar();
        }
    }
    
    public static void liberarHabitacion(int numero) {
        Habitacion hab = buscarHabitacion(numero);
        if(hab != null) {
            hab.liberar();
        }
    }
    
    public static double obtenerPrecioHabitacion(int numero) {
        Habitacion hab = buscarHabitacion(numero);
        if(hab != null) {
            return hab.getPrecio();
        }
        return 45000;
    }
    
    public static String obtenerInfoHotel() {
        return "--- HOTEL PARAISO ---\n\n" +
               "TIPOS DE HABITACIONES:\n" +
               "Estandar: 45,000 por noche\n" +
               "Premium: 65,000 por noche\n" +
               "Suite: 95,000 por noche\n\n" +
               "SERVICIOS ADICIONALES:\n" +
               "Cena: 10,000\n" +
               "Show nocturno: 7,000\n";
    }
}
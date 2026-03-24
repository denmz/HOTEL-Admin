package com.mycompany.gestionhotel;

import javax.swing.JOptionPane;

/**
 * Proyecto programacion 
 * Daniela Munoz Valverde 
 * Dereck Madrigal Zamora
 * José Adrián Reyes Hernandez
 */
public class GestionHotel {

    private static final int MAX = 50;
    private static Reservas[] reservas = new Reservas[MAX];
    private static int contReservas = 0;
    private static int contFacturas = 0;
    private static GestionHabitaciones gestionHabitaciones;

    public static void main(String[] args) {
        gestionHabitaciones = new GestionHabitaciones();
        
        int opcion;
        do {
            String menu = "--- HOTEL PARAISO ---\n\n";
            menu += "1. Registrar Cliente y Crear Reserva\n";
            menu += "2. Gestion de Clientes\n";
            menu += "3. Gestion de Reservas\n";
            menu += "4. Gestion de Habitaciones\n";
            menu += "5. Facturacion\n";
            menu += "6. Informacion del Hotel\n";
            menu += "7. Salir\n";
            
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    crearReserva();
                    break;
                case 2:
                    menuClientes();
                    break;
                case 3:
                    menuReservas();
                    break;
                case 4:
                    menuHabitaciones();
                    break;
                case 5:
                    menuFacturacion();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, GestionHabitaciones.obtenerInfoHotel());
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 7);
    }

    public static void crearReserva() {
        if (contReservas >= MAX) {
            JOptionPane.showMessageDialog(null, "No hay espacio para mas reservas.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Nombre completo:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        int cedula = Integer.parseInt(JOptionPane.showInputDialog("Cedula:"));
        int telefono = Integer.parseInt(JOptionPane.showInputDialog("Telefono:"));
        
        System.out.println("\n*** Revise la consola para ver las habitaciones disponibles ***");
        GestionHabitaciones.mostrarHabitacionesDisponibles();
        
        int habitacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese numero de habitacion (1-6):"));
        
        if(!GestionHabitaciones.verificarDisponibilidad(habitacion)) {
            JOptionPane.showMessageDialog(null, "La habitacion no esta disponible o no existe.");
            return;
        }
        
        double precioHabitacion = GestionHabitaciones.obtenerPrecioHabitacion(habitacion);
        Habitacion hab = GestionHabitaciones.buscarHabitacion(habitacion);
        TipoHabitacion tipoHabitacion = hab.getTipo();

        boolean cena = JOptionPane.showConfirmDialog(null, "Agregar cena?") == JOptionPane.YES_OPTION;
        boolean show = JOptionPane.showConfirmDialog(null, "Agregar show?") == JOptionPane.YES_OPTION;
        
        int codigoReserva = contReservas + 1;
        
        reservas[contReservas] = new Reservas(codigoReserva, cedula, nombre, edad, telefono, 
                                              habitacion, tipoHabitacion, precioHabitacion);
        
        if (cena) {
            reservas[contReservas].agregarCena(10000);
        }
        if (show) {
            reservas[contReservas].agregarShows(7000);
        }
        
        GestionHabitaciones.ocuparHabitacion(habitacion);
        
        JOptionPane.showMessageDialog(null, "Reserva creada exitosamente. Codigo: " + codigoReserva);
        
        System.out.println("\n--- Reserva Creada ---\n");
        System.out.println(reservas[contReservas].toString());
        System.out.println("---\n");
        
        contReservas++;
    }
    
    public static void menuClientes() {
        int opcion;
        do {
            String menu = "--- GESTION DE CLIENTES ---\n\n";
            menu += "1. Consultar cliente por cedula\n";
            menu += "2. Listar todos los clientes\n";
            menu += "3. Volver al menu principal\n";
            
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            
            switch(opcion) {
                case 1:
                    consultarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida.");
                    break;
            }
        } while(opcion != 3);
    }
    
    public static void consultarCliente() {
        if(contReservas == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
            return;
        }
        
        int cedulaBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cedula del cliente:"));
        
        Reservas clienteEncontrado = null;
        
        for(int i = 0; i < contReservas; i++) {
            if(reservas[i].getCedula() == cedulaBuscar) {
                clienteEncontrado = reservas[i];
                break;
            }
        }
        
        if(clienteEncontrado != null) {
            System.out.println("\n--- Informacion del Cliente ---\n");
            System.out.println(clienteEncontrado.toStringCliente());
            System.out.println("---\n");
            JOptionPane.showMessageDialog(null, "Cliente encontrado. Revise la consola.");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
        }
    }
    
    public static void listarClientes() {
        if(contReservas == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
            return;
        }
        
        System.out.println("\n--- Lista de Clientes ---\n");
        
        for(int i = 0; i < contReservas; i++) {
            System.out.println(reservas[i].toStringCliente());
            System.out.println("---");
        }
        System.out.println("");
        
        JOptionPane.showMessageDialog(null, "Se encontraron " + contReservas + " cliente(s). Revise la consola.");
    }
    
    public static void menuReservas() {
        int opcion;
        do {
            String menu = "--- GESTION DE RESERVAS ---\n\n";
            menu += "1. Consultar reserva por codigo\n";
            menu += "2. Listar todas las reservas\n";
            menu += "3. Cancelar reserva\n";
            menu += "4. Volver al menu principal\n";
            
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            
            switch(opcion) {
                case 1:
                    consultarReserva();
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    cancelarReserva();
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida.");
                    break;
            }
        } while(opcion != 4);
    }
    
    public static void consultarReserva() {
        if(contReservas == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas registradas.");
            return;
        }
        
        int codigoBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo de la reserva:"));
        
        Reservas reservaEncontrada = null;
        
        for(int i = 0; i < contReservas; i++) {
            if(reservas[i].getCodigoReserva() == codigoBuscar) {
                reservaEncontrada = reservas[i];
                break;
            }
        }
        
        if(reservaEncontrada != null) {
            System.out.println("\n--- Informacion de la Reserva ---\n");
            System.out.println(reservaEncontrada.toStringReserva());
            System.out.println("---\n");
            JOptionPane.showMessageDialog(null, "Reserva encontrada. Revise la consola.");
        } else {
            JOptionPane.showMessageDialog(null, "Reserva no encontrada.");
        }
    }
    
    public static void listarReservas() {
        if(contReservas == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas registradas.");
            return;
        }
        
        System.out.println("\n--- Lista de Reservas ---\n");
        
        for(int i = 0; i < contReservas; i++) {
            System.out.println(reservas[i].toStringReserva());
            System.out.println("---");
        }
        System.out.println("");
        
        JOptionPane.showMessageDialog(null, "Se encontraron " + contReservas + " reserva(s). Revise la consola.");
    }
    
    public static void cancelarReserva() {
        if(contReservas == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas para cancelar.");
            return;
        }
        
        int codigoBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo de la reserva a cancelar:"));
        
        Reservas reservaEncontrada = null;
        
        for(int i = 0; i < contReservas; i++) {
            if(reservas[i].getCodigoReserva() == codigoBuscar) {
                reservaEncontrada = reservas[i];
                break;
            }
        }
        
        if(reservaEncontrada != null) {
            reservaEncontrada.cancelarReserva();
            GestionHabitaciones.liberarHabitacion(reservaEncontrada.getNumeroHabitacion());
            
            System.out.println("\n--- Reserva Cancelada ---\n");
            System.out.println(reservaEncontrada.toStringReserva());
            System.out.println("---\n");
            
            JOptionPane.showMessageDialog(null, "Reserva cancelada exitosamente. Revise la consola.");
        } else {
            JOptionPane.showMessageDialog(null, "Reserva no encontrada.");
        }
    }
    
    public static void menuHabitaciones() {
        int opcion;
        do {
            String menu = "--- GESTION DE HABITACIONES ---\n\n";
            menu += "1. Ver habitaciones disponibles\n";
            menu += "2. Ver todas las habitaciones\n";
            menu += "3. Volver al menu principal\n";
            
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            
            switch(opcion) {
                case 1:
                    GestionHabitaciones.mostrarHabitacionesDisponibles();
                    JOptionPane.showMessageDialog(null, "Revise la consola.");
                    break;
                case 2:
                    GestionHabitaciones.mostrarTodasHabitaciones();
                    JOptionPane.showMessageDialog(null, "Revise la consola.");
                    break;
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida.");
                    break;
            }
        } while(opcion != 3);
    }

    public static void menuFacturacion() {
        int opcion;
        do {
            String menu = "--- FACTURACION ---\n\n";
            menu += "1. Generar factura\n";
            menu += "2. Consultar factura\n";
            menu += "3. Volver\n";
            
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    generarFactura();
                    break;
                case 2:
                    consultarFactura();
                    break;
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida.");
                    break;
            }
        } while(opcion != 3);
    }

    public static void generarFactura() {
        if (contReservas == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas para facturar.");
            return;
        }

        int codigoBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo de la reserva:"));
        
        Reservas reservaEncontrada = null;
        
        for (int i = 0; i < contReservas; i++) {
            if (reservas[i].getCodigoReserva() == codigoBuscar) {
                reservaEncontrada = reservas[i];
                break;
            }
        }

        if (reservaEncontrada == null) {
            JOptionPane.showMessageDialog(null, "Reserva no existe.");
            return;
        }
        
        if(reservaEncontrada.getCodigoFactura() != 0) {
            JOptionPane.showMessageDialog(null, "Esta reserva ya tiene una factura (Factura #" + 
                                          reservaEncontrada.getCodigoFactura() + ")");
            return;
        }

        contFacturas++;
        reservaEncontrada.setCodigoFactura(contFacturas);
        
        System.out.println("\n" + reservaEncontrada.toStringFactura());
        
        JOptionPane.showMessageDialog(null, "Factura #" + contFacturas + " generada. Revise la consola.");
    }
    
    public static void consultarFactura() {
        if (contFacturas == 0) {
            JOptionPane.showMessageDialog(null, "No hay facturas generadas.");
            return;
        }

        int codigoBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo de la factura:"));

        Reservas facturaEncontrada = null;
        
        for (int i = 0; i < contReservas; i++) {
            if (reservas[i].getCodigoFactura() == codigoBuscar) {
                facturaEncontrada = reservas[i];
                break;
            }
        }

        if (facturaEncontrada != null) {
            System.out.println("\n" + facturaEncontrada.toStringFactura());
            JOptionPane.showMessageDialog(null, "Factura encontrada. Revise la consola.");
        } else {
            JOptionPane.showMessageDialog(null, "Factura no encontrada.");
        }
    }
}
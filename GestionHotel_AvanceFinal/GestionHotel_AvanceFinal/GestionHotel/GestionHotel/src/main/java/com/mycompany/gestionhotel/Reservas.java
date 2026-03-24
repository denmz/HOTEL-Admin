package com.mycompany.gestionhotel;

/**
 * Proyecto programacion 
 * Daniela Munoz Valverde 
 * Dereck Madrigal Zamora
 * José Adrián Reyes Hernandez
 */
public class Reservas {
    private int codigoReservas;
    private int cedula;
    private String nombreCompleto;
    private int edad;
    private int telefono;
    private int numeroHabitacion;
    private TipoHabitacion tipoHabitacion;
    private double precioHabitacion;
    private boolean incluyeCena;
    private double precioCena;
    private boolean incluyeShows;
    private double precioShows;
    private double montoTotal;
    private String estadoReservas;
    private int codigoFactura;
    
    public Reservas() {
        this.estadoReservas = "Activa";
        this.codigoFactura = 0;
    }
    
    public Reservas(int codigoReserva, int cedula, String nombreCompleto, int edad, int telefono, 
                   int numeroHabitacion, TipoHabitacion tipoHabitacion, double precioHabitacion) {
        this.codigoReservas = codigoReserva;
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.telefono = telefono;
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precioHabitacion = precioHabitacion;
        this.incluyeCena = false;
        this.precioCena = 0;
        this.incluyeShows = false;
        this.precioShows = 0;
        this.estadoReservas = "Activa";
        this.codigoFactura = 0;
        calcularMontoTotal();
    }

    public int getCodigoReserva() {
        return codigoReservas;
    }

    public void setCodigoReserva(int codigoReservas) {
        this.codigoReservas = codigoReservas;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public double getPrecioHabitacion() {
        return precioHabitacion;
    }

    public void setPrecioHabitacion(double precioHabitacion) {
        this.precioHabitacion = precioHabitacion;
        calcularMontoTotal();
    }

    public boolean isIncluyeCena() {
        return incluyeCena;
    }

    public void setIncluyeCena(boolean incluyeCena) {
        this.incluyeCena = incluyeCena;
        calcularMontoTotal();
    }

    public double getPrecioCena() {
        return precioCena;
    }

    public void setPrecioCena(double precioCena) {
        this.precioCena = precioCena;
        calcularMontoTotal();
    }

    public boolean isIncluyeShows() {
        return incluyeShows;
    }

    public void setIncluyeShows(boolean incluyeShows) {
        this.incluyeShows = incluyeShows;
        calcularMontoTotal();
    }

    public double getPrecioShows() {
        return precioShows;
    }

    public void setPrecioShows(double precioShows) {
        this.precioShows = precioShows;
        calcularMontoTotal();
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public String getEstadoReserva() {
        return estadoReservas;
    }

    public void setEstadoReserva(String estadoReservas) {
        this.estadoReservas = estadoReservas;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }
    
    public void calcularMontoTotal() {
        montoTotal = precioHabitacion;
        
        if(incluyeCena) {
            montoTotal += precioCena;
        }
        
        if(incluyeShows) {
            montoTotal += precioShows;
        }
    }
    
    public void agregarCena(double precio) {
        this.incluyeCena = true;
        this.precioCena = precio;
        calcularMontoTotal();
    }
    
    public void agregarShows(double precio) {
        this.incluyeShows = true;
        this.precioShows = precio;
        calcularMontoTotal();
    }
    
    public void cancelarReserva() {
        this.estadoReservas = "Cancelada";
    }
    
    public String toStringCliente() {
        String info = "";
        info += "Cedula: " + cedula + "\n";
        info += "Nombre: " + nombreCompleto + "\n";
        info += "Edad: " + edad + "\n";
        info += "Telefono: " + telefono + "\n";
        info += "Tipo de Habitacion: " + tipoHabitacion + "\n";
        info += "Monto a Pagar: " + montoTotal + "\n";
        return info;
    }
    
    public String toStringReserva() {
        String info = "";
        info += "Codigo: " + codigoReservas + "\n";
        info += "Cliente: " + nombreCompleto + "\n";
        info += "Cedula: " + cedula + "\n";
        info += "Habitacion: " + numeroHabitacion + "\n";
        info += "Tipo: " + tipoHabitacion + "\n";
        info += "Precio habitacion: " + precioHabitacion + "\n";
        
        if(incluyeCena) {
            info += "Cena: Si (Precio: " + precioCena + ")\n";
        } else {
            info += "Cena: No\n";
        }
        
        if(incluyeShows) {
            info += "Shows: Si (Precio: " + precioShows + ")\n";
        } else {
            info += "Shows: No\n";
        }
        
        info += "Monto total: " + montoTotal + "\n";
        info += "Estado: " + estadoReservas + "\n";
        
        return info;
    }
    
    public String toStringFactura() {
        String info = "--- FACTURA #" + codigoFactura + " ---\n\n";
        info += "DATOS DEL CLIENTE:\n";
        info += "Nombre: " + nombreCompleto + "\n";
        info += "Cedula: " + cedula + "\n";
        info += "Telefono: " + telefono + "\n\n";
        info += "DETALLES DE LA RESERVA:\n";
        info += "Codigo Reserva: " + codigoReservas + "\n";
        info += "Habitacion: " + numeroHabitacion + " (" + tipoHabitacion + ")\n";
        info += "Precio habitacion: " + precioHabitacion + "\n";
        
        if(incluyeCena) {
            info += "Cena: " + precioCena + "\n";
        }
        
        if(incluyeShows) {
            info += "Show nocturno: " + precioShows + "\n";
        }
        
        info += "\n---\n";
        info += "TOTAL A PAGAR: " + montoTotal + "\n";
        info += "---\n";
        
        return info;
    }

    @Override
    public String toString() {
        return toStringReserva();
    }
}
    
    
    
    
    


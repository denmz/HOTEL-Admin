package com.mycompany.gestionhotel;

/**
 * Proyecto programacion 
 * Daniela Muñoz Valverde 
 * Dereck Madrigal Zamora
 * José Adrián Reyes Hernandez
 */
public class Habitacion {
    private int numero;
    private TipoHabitacion tipo;
    private double precio;
    private boolean disponible;

    public Habitacion(int numero, TipoHabitacion tipo, double precio) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = true;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public void ocupar() {
        this.disponible = false;
    }
    
    public void liberar() {
        this.disponible = true;
    }

    @Override
    public String toString() {
        String estado = disponible ? "Disponible" : "Ocupada";
        return "Habitacion #" + numero + 
               "\nTipo: " + tipo + 
               "\nPrecio: " + precio + 
               "\nEstado: " + estado;
    }
}
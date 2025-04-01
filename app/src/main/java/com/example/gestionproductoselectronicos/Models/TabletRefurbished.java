package com.example.gestionproductoselectronicos.Models;

public class TabletRefurbished extends Tablet {
    public char nivel_reacondicionamiento;

    public TabletRefurbished(String marca, String modelo, double precio_base, String anio_lanzamiento, int stock, int number, double pantalla_pulgadas, boolean soporte_pen, char nivel_reacondicionamiento)
    {
        super(marca, modelo, precio_base, anio_lanzamiento, stock, number, pantalla_pulgadas, soporte_pen);
        this.nivel_reacondicionamiento = nivel_reacondicionamiento;
    }

    @Override
    public double calcular_precio()
    {
        return super.calcular_precio() - (pantalla_pulgadas * 1.5) - (nivel_reacondicionamiento == 'C' ? 100 : 0);
    }

    @Override
    public String mostrarDetalles()
    {
        return super.mostrarDetalles() +
                "\nNivel Reacondicionamiento: " + nivel_reacondicionamiento;
    }
}

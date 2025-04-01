package com.example.gestionproductoselectronicos.Models;

public class Tablet extends Dispositivo
{
    public double pantalla_pulgadas;
    public boolean soporte_pen;

    public double getPantalla_pulgadas() {
        return pantalla_pulgadas;
    }

    public boolean isSoporte_pen() {
        return soporte_pen;
    }

    public Tablet(String marca, String modelo, double precio_base, String anio_lanzamiento, int stock, int number, double pantalla_pulgadas, boolean soporte_pen)
    {
        super(marca, modelo, precio_base, anio_lanzamiento, stock, number);
        this.pantalla_pulgadas = pantalla_pulgadas;
        this.soporte_pen = soporte_pen;
    }

    @Override
    public double calcular_precio()
    {
        return super.calcular_precio() *  0.85 - (pantalla_pulgadas * 1.5);
    }

    @Override
    public String mostrarDetalles ()
    {
        return super.mostrarDetalles() + "\nPantalla Pulgadas: " + pantalla_pulgadas + "\nSoporte Pen: " + soporte_pen;
    }
}

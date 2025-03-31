package com.example.gestionproductoselectronicos.Models;

public class Smartphone extends Dispositivo
{
    public int almacenamiento_gb;
    public int cantidad_camaras;

    public int getAlmacenamiento_gb() {
        return almacenamiento_gb;
    }

    public int getCantidad_camaras() {
        return cantidad_camaras;
    }

    public Smartphone(String marca, String modelo, double precio_base, String anio_lanzamiento, int stock, int almacenamiento_gb, int cantidad_camaras)
    {
        super(marca, modelo, precio_base, anio_lanzamiento, stock);
        this.almacenamiento_gb = almacenamiento_gb;
        this.cantidad_camaras = cantidad_camaras;
    }

    @Override
    public double calcular_precio()
    {
        return super.calcular_precio() - (almacenamiento_gb * 2) - (cantidad_camaras * 10);
    }

    @Override
    public String mostrarDetalles ()
    {
        return super.mostrarDetalles() + "\nAlmacenamieno GB: " + stock + "\nCantidad de Cámaras: " + cantidad_camaras;
    }
}

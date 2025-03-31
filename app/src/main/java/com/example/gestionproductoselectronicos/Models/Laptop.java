package com.example.gestionproductoselectronicos.Models;

public class Laptop extends Dispositivo
{
    public String procesador;
    public int ram_gb;
    public int almacenamiento_gb;
    public boolean enPromocion;
    public double descuentoPromocion;

    public String getProcesador() {
        return procesador;
    }

    public int getRam_gb() {
        return ram_gb;
    }

    public int getAlmacenamiento_gb() {
        return almacenamiento_gb;
    }

    public Laptop(String marca, String modelo, double precio_base, String anio_lanzamiento, int stock, String procesador, int ram_gb, int almacenamiento_gb, boolean enPromocion, double descuentoPromocion)
    {
        super(marca, modelo, precio_base, anio_lanzamiento, stock);
        this.procesador = procesador;
        this.ram_gb = ram_gb;
        this.almacenamiento_gb = almacenamiento_gb;
        this.enPromocion = false;
        this.descuentoPromocion = 0;
    }

    @Override
    public double calcular_precio()
    {
        double precio = precio_base;
        if(enPromocion)
        {
            precio *= (1 - descuentoPromocion);
        }
        return precio;
    }

    @Override
    public String mostrarDetalles ()
    {
        return super.mostrarDetalles() + "\nProcesador: " + procesador + "\nRAM GB: " + ram_gb + "\nAlmacenamiento GB: " + almacenamiento_gb + "\n¿Tiene Promoción? " + enPromocion +"\nDescuento: " + descuentoPromocion;
    }
}

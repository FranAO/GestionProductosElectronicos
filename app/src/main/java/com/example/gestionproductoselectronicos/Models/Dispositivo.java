package com.example.gestionproductoselectronicos.Models;

public class Dispositivo
{
    public String marca;
    public String modelo;
    public double precio_base;
    public String anio_lanzamiento;
    public int stock;
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecio_base() {
        return precio_base;
    }

    public String getAnio_lanzamiento() {
        return anio_lanzamiento;
    }

    public int getStock() {
        return stock;
    }

    public Dispositivo(String marca, String modelo, double precio_base, String anio_lanzamiento, int stock)
    {
        this.marca = marca;
        this.modelo = modelo;
        this.precio_base = precio_base;
        this.anio_lanzamiento = anio_lanzamiento;
        this.stock = stock;
    }

    public String toString()
    {
        return marca + " " + modelo + " (" + anio_lanzamiento + ")";
    }
    public double calcular_precio()
    {
        return precio_base;
    }

    public String mostrarDetalles ()
    {
        return "Marca: " + marca + "\nModelo: " + modelo + "\nPrecio Base: " + precio_base + "\nAño Lanzamiento: " + anio_lanzamiento + "\nStock: " + stock;
    }
}

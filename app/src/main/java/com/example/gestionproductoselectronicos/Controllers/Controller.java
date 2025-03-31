package com.example.gestionproductoselectronicos.Controllers;

import com.example.gestionproductoselectronicos.Models.Dispositivo;
import com.example.gestionproductoselectronicos.Models.Laptop;
import com.example.gestionproductoselectronicos.Models.Smartphone;
import com.example.gestionproductoselectronicos.Models.Tablet;
import com.example.gestionproductoselectronicos.Models.TabletRefurbished;
import java.util.ArrayList;
import java.util.List;

public class Controller
{
    private static Controller instance;

    public List<Dispositivo> dispositivos = new ArrayList<>();
    private Controller()
    {
        dispositivos.add(new Laptop("Dell", "XPS 15", 1500.00, "2023", 10, "Intel Core i7", 16, 512, false, 0));
        dispositivos.add(new Smartphone("Samsung", "Galaxy S23", 999.99, "2023", 25, 256, 3));
        dispositivos.add(new Tablet("Apple", "iPad Air", 599.00, "2022", 15, 10.9, true));
        dispositivos.add(new TabletRefurbished("Lenovo", "Tab P11", 299.99, "2021", 5, 11.0, false, 'B'));
    }

    public Dispositivo buscarDispositivo(String marca, String modelo)
    {
        for (Dispositivo reserva : dispositivos)
        {
            if (reserva.getMarca().equals(marca) || reserva.getModelo().equals(modelo))
            {
                return reserva;
            }
        }
        return null;
    }

    public static Controller getInstance()
    {
        if (instance == null)
        {
            instance = new Controller();
        }
        return instance;
    }

    public List<Dispositivo> getDispositivos()
    {
        return this.dispositivos;
    }
}

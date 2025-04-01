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
        dispositivos.add(new Laptop("HP", "Spectre x360", 1299.99, "2023", 8, 80,"Intel Core i5", 8, 256, true, 0));
        dispositivos.add(new Laptop("Apple", "MacBook Pro 14", 1999.00, "2023", 5, 80,"Apple M2 Pro", 16, 512, false, 0));
        dispositivos.add(new Laptop("Asus", "ROG Zephyrus G14", 1499.99, "2023", 6, 70,"AMD Ryzen 9", 32, 1000, true, 0));
        dispositivos.add(new Smartphone("Apple", "iPhone 15", 1099.00, "2023", 20, 100, 128, 5));
        dispositivos.add(new Smartphone("Xiaomi", "Redmi Note 12", 299.99, "2023", 30, 64, 4, 3));
        dispositivos.add(new Smartphone("Google", "Pixel 7 Pro", 899.00, "2022", 15, 128, 5, 4));
        dispositivos.add(new Tablet("Samsung", "Galaxy Tab S8", 699.99, "2023", 12, 100,11.0, true));
        dispositivos.add(new Tablet("Huawei", "MatePad Pro", 549.99, "2022", 7, 300,12.6, false));
        dispositivos.add(new TabletRefurbished("Apple", "iPad Pro 2020", 499.99, "2020", 3, 40,11.0, true, 'A'));
        dispositivos.add(new TabletRefurbished("Samsung", "Galaxy Tab S7", 349.99, "2021", 4, 60,11.0, false, 'C'));
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

    public List<Dispositivo> buscarPorTexto(String textoBusqueda)
    {
        List<Dispositivo> dispositivoEncontrado = new ArrayList<>();

        if (textoBusqueda.isEmpty())
        {
            return dispositivos;
        }

        for (Dispositivo dispositivo : dispositivos)
        {
            if (dispositivo instanceof Laptop && "laptop".equalsIgnoreCase(textoBusqueda) ||
                dispositivo instanceof Smartphone && "smartphone".equalsIgnoreCase(textoBusqueda) ||
                dispositivo instanceof Tablet && "tablet".equalsIgnoreCase(textoBusqueda) ||
                dispositivo instanceof TabletRefurbished && "tabletrefurbished".equalsIgnoreCase(textoBusqueda)) {
                dispositivoEncontrado.add(dispositivo);
            }

            if (dispositivo.getMarca().equalsIgnoreCase(textoBusqueda) ||
                dispositivo.getModelo().equalsIgnoreCase(textoBusqueda) ||
                dispositivo.getAnio_lanzamiento().equals(textoBusqueda))
            {
                dispositivoEncontrado.add(dispositivo);
            }
        }

        return dispositivoEncontrado;
    }
}

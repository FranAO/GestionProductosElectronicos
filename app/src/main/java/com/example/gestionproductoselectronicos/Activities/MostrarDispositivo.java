package com.example.gestionproductoselectronicos.Activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gestionproductoselectronicos.Controllers.Controller;
import com.example.gestionproductoselectronicos.Models.Dispositivo;
import com.example.gestionproductoselectronicos.Models.Laptop;
import com.example.gestionproductoselectronicos.Models.Smartphone;
import com.example.gestionproductoselectronicos.Models.Tablet;
import com.example.gestionproductoselectronicos.Models.TabletRefurbished;
import com.example.gestionproductoselectronicos.R;

import java.util.ArrayList;

public class MostrarDispositivo extends AppCompatActivity
{
    private Spinner spinnerDispositivos;
    private TextView tvResultado;
    private TextView tvEstadisticas;
    private Button btnMostrar;
    private ArrayAdapter<String> spinnerAdapter;
    private ArrayList<String> nombresDispositivos = new ArrayList<>();
    private Controller controllerDispositivo;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostrar_dispositivo);

        controllerDispositivo = Controller.getInstance();
        init();
    }

    private void init ()
    {
        spinnerDispositivos = findViewById(R.id.spinnerDispositivo);
        tvResultado = findViewById(R.id.tvResultado);
        tvEstadisticas = findViewById(R.id.tvEstadisticas);
        btnMostrar = findViewById(R.id.btnMostrar);

        actualizarListaDispositivo();
        actualizarEstadisticas();

        btnMostrar.setOnClickListener(view -> mostrarDetallesDispositivoSeleccionado());
    }

    private void actualizarListaDispositivo()
    {
        nombresDispositivos.clear();
        for (Dispositivo dispositivo : controllerDispositivo.dispositivos)
        {
            nombresDispositivos.add(dispositivo.getMarca() + " - " + dispositivo.getModelo());
        }

        if (spinnerAdapter == null)
        {
            spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresDispositivos);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDispositivos.setAdapter(spinnerAdapter);
        } else
        {
            spinnerAdapter.notifyDataSetChanged();
        }
    }
    private void mostrarDetallesDispositivoSeleccionado()
    {
        int posicionSeleccionada = spinnerDispositivos.getSelectedItemPosition();

        if (posicionSeleccionada >= 0 && posicionSeleccionada < controllerDispositivo.dispositivos.size())
        {
            Dispositivo dispositivo = controllerDispositivo.dispositivos.get(posicionSeleccionada);
            String detalles = "Tipo: " + dispositivo.getClass().getSimpleName() + "\n\n"
                                       + dispositivo.mostrarDetalles()
                                       + "\nPrecio Total: $" + dispositivo.calcular_precio();
            tvResultado.setText(detalles);
        }
    }

    private void actualizarEstadisticas() {
        int totalDispositivos = controllerDispositivo.dispositivos.size();

        if (totalDispositivos == 0) {
            tvEstadisticas.setText("No hay dispositivos registrados");
            return;
        }

        int laptops = 0, smartphones = 0, tablets = 0, tabletsRefurb = 0;
        double ingresosTotales = 0;
        double promedioPrecios = 0;
        int stockTotal = 0;

        // Nuevas variables para acumular los valores de number
        int totalNumberLaptops = 0;
        int totalNumberSmartphones = 0;
        int totalNumberTablets = 0;
        int totalNumberTabletsRefurb = 0;

        for (Dispositivo dispositivo : controllerDispositivo.dispositivos)
        {
            ingresosTotales += dispositivo.calcular_precio();
            stockTotal += dispositivo.getStock();

            if (dispositivo instanceof Laptop)
            {
                laptops++;
                totalNumberLaptops += dispositivo.getNumber();
            } else if (dispositivo instanceof Smartphone)
            {
                smartphones++;
                totalNumberSmartphones += dispositivo.getNumber();
            } else if (dispositivo instanceof TabletRefurbished)
            {
                tabletsRefurb++;
                totalNumberTabletsRefurb += dispositivo.getNumber();
            } else if (dispositivo instanceof Tablet)
            {
                tablets++;
                totalNumberTablets += dispositivo.getNumber();
            }
        }

        promedioPrecios = ingresosTotales / totalDispositivos;

        String statsText = String.format(
                "Estadísticas de Dispositivos:\n\n" +
                        "• Total dispositivos: %d\n" +
                        "• Stock total: %d unidades\n" +
                        "• Valor total en inventario: $%.2f\n" +
                        "• Precio promedio: $%.2f\n\n" +
                        "Distribución por tipo:\n" +
                        "  - Laptops: %d (%.0f%%)\n" +
                        "  - Smartphones: %d (%.0f%%)\n" +
                        "  - Tablets: %d (%.0f%%)\n" +
                        "  - Tablets Refurbished: %d (%.0f%%)\n\n" +
                        "Resumen por número (number):\n" +
                        "  - Laptops: %d\n" +
                        "  - Smartphones: %d\n" +
                        "  - Tablets: %d\n" +
                        "  - Tablets Refurbished: %d",
                totalDispositivos,
                stockTotal,
                ingresosTotales,
                promedioPrecios,
                laptops, (laptops * 100f / totalDispositivos),
                smartphones, (smartphones * 100f / totalDispositivos),
                tablets, (tablets * 100f / totalDispositivos),
                tabletsRefurb, (tabletsRefurb * 100f / totalDispositivos),
                totalNumberLaptops,
                totalNumberSmartphones,
                totalNumberTablets,
                totalNumberTabletsRefurb
        );

        tvEstadisticas.setText(statsText);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        actualizarListaDispositivo();
        actualizarEstadisticas();
    }
}
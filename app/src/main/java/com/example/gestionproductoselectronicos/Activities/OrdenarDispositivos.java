package com.example.gestionproductoselectronicos.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrdenarDispositivos extends AppCompatActivity
{
    public TextView tvResultado;
    private Controller controllerDispositivo;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ordenar_dispositivos);

        controllerDispositivo = Controller.getInstance();
        init();
    }

    private void init()
    {
        tvResultado = findViewById(R.id.tvResultado);
        Button btnOrdenar = findViewById(R.id.btnOrdenar);

        btnOrdenar.setOnClickListener(v -> {
            List<Dispositivo> dispositivos = controllerDispositivo.getDispositivos();

            Collections.sort(dispositivos, new Comparator<Dispositivo>()
            {
                @Override
                public int compare(Dispositivo d1, Dispositivo d2)
                {
                    return Integer.compare(d2.getStock(), d1.getStock());
                }
            });

            mostrarDispositivosOrdenados(dispositivos);
        });
    }

    private void mostrarDispositivosOrdenados(List<Dispositivo> dispositivos)
    {
        StringBuilder sb = new StringBuilder();
        for (Dispositivo dispositivo : dispositivos)
        {
            sb.append(dispositivo.toString())
                    .append("\nPrecio: $")
                    .append(String.format("%.2f", dispositivo.calcular_precio()))
                    .append("\nStock: ")
                    .append(dispositivo.getStock())
                    .append("\n\n");
        }
        tvResultado.setText(sb.toString());
    }
}
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
import com.example.gestionproductoselectronicos.R;
import com.example.gestionproductoselectronicos.Models.Dispositivo;

import java.util.List;


public class BuscarDispositivo extends AppCompatActivity
{
    public EditText etBuscar;
    public TextView tvResultado;
    private Controller controllerDispositivo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar_dispositivo);

        controllerDispositivo = Controller.getInstance();
        init();

    }

    private void init ()
    {
        etBuscar = findViewById(R.id.etBuscar);
        tvResultado = findViewById(R.id.tvResultado);
        Button btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(v -> {
            String textoBusqueda = etBuscar.getText().toString().trim();

            List<Dispositivo> dispositivoEncontrado = controllerDispositivo.buscarPorTexto(textoBusqueda);

            if (!dispositivoEncontrado.isEmpty())
            {
                StringBuilder resultado = new StringBuilder();
                for (Dispositivo dispositivo : dispositivoEncontrado)
                {
                    resultado.append("Tipo:")
                            .append(dispositivo.getClass().getSimpleName() + "\n")
                            .append(dispositivo.mostrarDetalles())
                            .append("\nPrecio Total: $")
                            .append(dispositivo.calcular_precio())
                            .append("\n\n");
                }
                tvResultado.setText(resultado.toString());
            } else {
                tvResultado.setText("No se encontraron reservas.");
            }
        });
    }
}
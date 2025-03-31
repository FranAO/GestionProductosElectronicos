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

            Dispositivo dispositivoEncontrado = controllerDispositivo.buscarDispositivo(textoBusqueda, textoBusqueda);

            if (dispositivoEncontrado != null)
            {
                tvResultado.setText(dispositivoEncontrado.mostrarDetalles() + "\nPrecio: " + dispositivoEncontrado.calcular_precio());
            } else
            {
                tvResultado.setText("No se encontró ningún dispositivo.");
            }
        });
    }
}
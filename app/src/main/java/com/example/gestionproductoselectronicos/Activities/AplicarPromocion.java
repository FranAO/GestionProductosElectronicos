package com.example.gestionproductoselectronicos.Activities;

import android.os.Bundle;
import android.view.View;
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
import com.example.gestionproductoselectronicos.R;

public class AplicarPromocion extends AppCompatActivity
{
    public EditText etProcesador;
    public EditText etPromo;
    public TextView tvResultado;
    private Controller controllerDispositivo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aplicar_promocion);

        controllerDispositivo = Controller.getInstance();
        init();
    }

    private void init ()
    {
        etProcesador = findViewById(R.id.etProcesador);
        etPromo = findViewById(R.id.etPromo);
        tvResultado = findViewById(R.id.tvResultado);
        Button btnAplicarPromo = findViewById(R.id.btnAplicarPromo);
        controllerDispositivo = Controller.getInstance();

        btnAplicarPromo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String procesadorBuscado = etProcesador.getText().toString();
                String descuentoTexto = etPromo.getText().toString();

                double descuento = Double.parseDouble(descuentoTexto) / 100;

                boolean encontrada = false;

                for(Dispositivo dispositivo : controllerDispositivo.dispositivos)
                {
                    if(dispositivo instanceof Laptop)
                    {
                        Laptop laptop = (Laptop) dispositivo;

                        if(laptop.procesador.equalsIgnoreCase(procesadorBuscado))
                        {
                            laptop.enPromocion = true;
                            laptop.descuentoPromocion = descuento;
                            encontrada = true;

                            String mensaje = "Promoción aplicada:\n" +
                                    "Marca: " + laptop.marca + "\n" +
                                    "Modelo: " + laptop.modelo + "\n" +
                                    "Precio con descuento: $" + laptop.calcular_precio();

                            tvResultado.setText(mensaje);
                        }
                    }
                }

                if(!encontrada) {
                    tvResultado.setText("No se encontró laptop con ese procesador");
                }
            }
        });
    }
}
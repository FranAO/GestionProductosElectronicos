package com.example.gestionproductoselectronicos.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gestionproductoselectronicos.Controllers.Controller;
import com.example.gestionproductoselectronicos.Models.Laptop;
import com.example.gestionproductoselectronicos.Models.Smartphone;
import com.example.gestionproductoselectronicos.Models.Tablet;
import com.example.gestionproductoselectronicos.Models.TabletRefurbished;
import com.example.gestionproductoselectronicos.R;

public class AgregarDispositivos extends AppCompatActivity
{
    private EditText etMarca;
    private EditText etModelo;
    private EditText etTarifaBase;
    private EditText etAnioLanzamiento;
    private EditText etStock;
    private EditText etAlmacenamientoGB;
    private EditText etCantidadCamaras;
    private EditText etProcesador;
    private EditText etRamGB;
    private EditText etPantallaPulgadas;
    private EditText etNivelReacondicionamiento;
    private Spinner spinnerDispositivo;
    private Spinner spinnerSoportePen;
    private Controller controllerDispositivo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_dispositivos);

        controllerDispositivo = Controller.getInstance();
        init();
        setupSpinners();
        setupButtonListeners();
    }

    private void init()
    {
        etMarca = findViewById(R.id.etMarca);
        etModelo = findViewById(R.id.etModelo);
        etTarifaBase = findViewById(R.id.etTarifaBase);
        etAnioLanzamiento = findViewById(R.id.etAnioLanzamiento);
        etStock = findViewById(R.id.etStock);
        etAlmacenamientoGB = findViewById(R.id.etAlmacenamientoGB);
        etCantidadCamaras = findViewById(R.id.etCantidadCamaras);
        etProcesador = findViewById(R.id.etProcesador);
        etRamGB = findViewById(R.id.etRamGB);
        etPantallaPulgadas = findViewById(R.id.etPantallaPulgadas);
        spinnerSoportePen = findViewById(R.id.spinnerSoportePen);
        etNivelReacondicionamiento = findViewById(R.id.etNivelReacondicionamiento);
        spinnerDispositivo = findViewById(R.id.spinnerDispositivo);
    }

    private void setupSpinners()
    {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.opciones_si_no,
                android.R.layout.simple_spinner_dropdown_item);
        spinnerSoportePen.setAdapter(adapter);

        spinnerDispositivo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Visibility(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
    private void setupButtonListeners()
    {
        Button btnAgregarDispositivo = findViewById(R.id.btnAgregarDispositivo);
        btnAgregarDispositivo.setOnClickListener(v -> agregarDispositivo());
    }
    private void Visibility(String tipoDispositivo)
    {
        etAlmacenamientoGB.setVisibility(View.GONE);
        etCantidadCamaras.setVisibility(View.GONE);
        etProcesador.setVisibility(View.GONE);
        etRamGB.setVisibility(View.GONE);
        etPantallaPulgadas.setVisibility(View.GONE);
        spinnerSoportePen.setVisibility(View.GONE);
        etNivelReacondicionamiento.setVisibility(View.GONE);

        switch (tipoDispositivo)
        {
            case "Laptop":
                etAlmacenamientoGB.setVisibility(View.VISIBLE);
                etProcesador.setVisibility(View.VISIBLE);
                etRamGB.setVisibility(View.VISIBLE);
            break;
            case "Smartphone":
                etAlmacenamientoGB.setVisibility(View.VISIBLE);
                etCantidadCamaras.setVisibility(View.VISIBLE);
            break;
            case "Tablet":
                etPantallaPulgadas.setVisibility(View.VISIBLE);
                spinnerSoportePen.setVisibility(View.VISIBLE);
            break;
            case "TabletRefurbished":
                etPantallaPulgadas.setVisibility(View.VISIBLE);
                etNivelReacondicionamiento.setVisibility(View.VISIBLE);
            break;
        }
    }

    private void agregarDispositivo()
    {
        String marca = etMarca.getText().toString();
        String modelo = etModelo.getText().toString();
        double tarifa = Double.parseDouble(etTarifaBase.getText().toString());
        String anio = etAnioLanzamiento.getText().toString();
        int stock = Integer.parseInt(etStock.getText().toString());
        String tipoDispositivo = spinnerDispositivo.getSelectedItem().toString();

        switch (tipoDispositivo)
        {
            case "Laptop":
            {
                int almacenamientoGB = Integer.parseInt(etAlmacenamientoGB.getText().toString());
                String procesador = etProcesador.getText().toString();
                int ramGB = Integer.parseInt(etRamGB.getText().toString());
                controllerDispositivo.dispositivos.add(new Laptop(marca, modelo, tarifa, anio, stock, procesador, ramGB, almacenamientoGB, false, 0));
                break;
            }
            case "Smartphone":
            {
                int almacenamientoSmartphone = Integer.parseInt(etAlmacenamientoGB.getText().toString());
                int cantidadCamaras = Integer.parseInt(etCantidadCamaras.getText().toString());
                controllerDispositivo.dispositivos.add(new Smartphone(marca, modelo, tarifa, anio, stock, almacenamientoSmartphone, cantidadCamaras
                ));
                break;
            }
            case "Tablet":
            {
                double pantallaTablet = Double.parseDouble(etPantallaPulgadas.getText().toString());
                boolean soportePen = spinnerSoportePen.getSelectedItem().toString().equals("Sí");
                controllerDispositivo.dispositivos.add(new Tablet(marca, modelo, tarifa, anio, stock, pantallaTablet, soportePen));
            break;
            }
            case "TabletRefurbished":
            {
                double pantallaRefurb = Double.parseDouble(etPantallaPulgadas.getText().toString());
                String nivelStr = etNivelReacondicionamiento.getText().toString().toUpperCase();
                if(!nivelStr.matches("[ABC]"))
                {
                    Toast.makeText(this, "Nivel debe ser A, B o C", Toast.LENGTH_SHORT).show();
                    return;
                }
                char nivelReacondicionamiento = nivelStr.charAt(0);
                controllerDispositivo.dispositivos.add(new TabletRefurbished(marca, modelo, tarifa, anio, stock, pantallaRefurb, false, nivelReacondicionamiento));
            break;
            }
        }
        limpiarCampos();
    }

    private void limpiarCampos()
    {
        etMarca.setText("");
        etModelo.setText("");
        etTarifaBase.setText("");
        etAnioLanzamiento.setText("");
        etStock.setText("");
        etAlmacenamientoGB.setText("");
        etCantidadCamaras.setText("");
        etProcesador.setText("");
        etRamGB.setText("");
        etPantallaPulgadas.setText("");
        etNivelReacondicionamiento.setText("");
    }
}
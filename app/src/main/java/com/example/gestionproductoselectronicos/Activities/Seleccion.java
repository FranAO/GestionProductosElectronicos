package com.example.gestionproductoselectronicos.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gestionproductoselectronicos.R;

public class Seleccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccion);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }

        int[] cardIds = {R.id.card1, R.id.card2, R.id.card3, R.id.card4, R.id.card5, R.id.card6};
        for (int id : cardIds)
        {
            View card = findViewById(id);
            if (card != null)
            {
                card.setOnClickListener(v -> {
                    if (id == R.id.card1)
                    {
                        startActivity(new Intent(Seleccion.this, AgregarDispositivos.class));
                    } else if (id == R.id.card2)
                    {
                        startActivity(new Intent(Seleccion.this, BuscarDispositivo.class));
                    } else if (id == R.id.card3)
                    {
                        startActivity(new Intent(Seleccion.this, AplicarPromocion.class));
                    } else if (id == R.id.card4)
                    {
                        startActivity(new Intent(Seleccion.this, MostrarDispositivo.class));
                    } else if (id == R.id.card5)
                    {
                        startActivity(new Intent(Seleccion.this, OrdenarDispositivos.class));
                    } else if (id == R.id.card6)
                    {
                        finish();
                    }
                });
            }
        }
    }
}
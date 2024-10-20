package com.example.elevencash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImageButton btnVenda = findViewById(R.id.btnVenda);
        ImageButton btnRelatorio = findViewById(R.id.btnRelatorio);

        btnVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent venda = new Intent(MainActivity.this, TelaVendas.class);
                startActivity(venda);
            }
        });

        btnRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent relatorio = new Intent(MainActivity.this, TelaRelatorios.class);
                startActivity(relatorio);
            }
        });

    }
}
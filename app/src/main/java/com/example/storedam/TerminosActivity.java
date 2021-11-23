package com.example.storedam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TerminosActivity extends AppCompatActivity {
    private Button btn_aceptaTerminos;
    private Button btn_noAceptaTerminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos);

        btn_aceptaTerminos = findViewById(R.id.btn_aceptaTerminos);
        btn_noAceptaTerminos = findViewById(R.id.btn_noAceptaTerminos);

        btn_aceptaTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("ESTADO", "ACEPTADO");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        btn_noAceptaTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
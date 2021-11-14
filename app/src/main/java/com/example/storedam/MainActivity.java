package com.example.storedam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_registro;
    private Button btn_iniciar;
    private EditText edt_usuario;
    private EditText edt_contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_registro = findViewById(R.id.btn_registro);
        btn_iniciar = findViewById(R.id.btn_iniciar);
        edt_usuario = findViewById(R.id.edt_usuario);
        edt_contrasena = findViewById(R.id.edt_contrasena);

        btn_registro.setOnClickListener(this);
        btn_iniciar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_iniciar:
                String usuario = edt_usuario.getText().toString();
                String contrasena = edt_contrasena.getText().toString();

                Log.e("USUARIO", usuario);
                Log.e("CONTRASEÑA", contrasena);

                if (usuario.equals("admin@admin.com") && contrasena.equals("admin")) {
                    Toast.makeText(this, "Se ha inciado Sesion", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Error iniciando sesion", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.btn_registro:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);
                break;
        }
    }
}
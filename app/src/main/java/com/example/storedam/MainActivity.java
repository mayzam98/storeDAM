package com.example.storedam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.storedam.util.Constant;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_registro;
    private Button btn_iniciar;
    private TextInputLayout edt_usuario;
    private TextInputLayout edt_contrasena;

    private final int ACTIVITY_REGISTRO = 1;

    private SharedPreferences misPreferencias;

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

        misPreferencias = getSharedPreferences(Constant.PREFERENCE, MODE_PRIVATE);
        String usuario = misPreferencias.getString("usuario", "");
        String contrasena = misPreferencias.getString("contraseña", "");
        if(!usuario.equals("") && !contrasena.equals("")){
            toLogin(usuario, contrasena);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_iniciar:
                String usuario = edt_usuario.getEditText().getText().toString();
                String contrasena = edt_contrasena.getEditText().getText().toString();

                Log.e("USUARIO", usuario);
                Log.e("CONTRASEÑA", contrasena);

                if (usuario.equals("admin@admin.com") && contrasena.equals("admin")) {
                    toLogin(usuario, contrasena);
                } else {
                    Toast.makeText(this, "Error iniciando sesion", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.btn_registro:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivityForResult(intent,ACTIVITY_REGISTRO);
                break;
        }
    }

    public void toLogin(String usuario, String contrasena){
        Toast.makeText(this, "Se ha inciado Sesion", Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = misPreferencias.edit();
        editor.putString("usuario", usuario);
        editor.putString("contraseña", contrasena);
        editor.commit();

        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("Usuario", usuario);
        intent.putExtra("contraseña", contrasena);
        intent.putExtra("number", 1);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (ACTIVITY_REGISTRO == requestCode){
                if (resultCode == Activity.RESULT_OK){
                    String usuario = data.getStringExtra("correo");
                    String contrasena = data.getStringExtra("contraseña");
                    toLogin(usuario, contrasena);
                }
            }
    }
}
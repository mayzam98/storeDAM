package com.example.storedam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {
    private CheckBox chb_terminos;
    private Button btn_terminar;
    private TextView link_terminos;
    private EditText edt_contrasena;
    private EditText edt_correo;
    private final int ACTIVITY_TERMINOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        chb_terminos = findViewById(R.id.chb_terminos);
        btn_terminar = findViewById(R.id.btn_terminar);
        link_terminos = findViewById(R.id.link_terminos);
        edt_contrasena = findViewById(R.id.edt_contrasena);
        edt_correo = findViewById(R.id.edt_correo);

        chb_terminos.setEnabled(false);
        btn_terminar.setEnabled(false);

        link_terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this, TerminosActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        chb_terminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btn_terminar.setEnabled(isChecked);
            }
        });
        btn_terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contrasena = edt_contrasena.getText().toString();
                if(contrasena.length()<8 && !isValidPassword(contrasena)){
                    Toast.makeText(RegistroActivity.this, "CONTRASEÑA NO VALIDA", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegistroActivity.this, "CONTRASEÑA VALIDA", Toast.LENGTH_SHORT).show();

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("correo", edt_correo.getText().toString());
                    resultIntent.putExtra("contraseña", edt_contrasena.getText().toString());
                    setResult(Activity.RESULT_OK,resultIntent);
                    finish();

                }
            }
        });
    }

    public static boolean isValidPassword(final String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_TERMINOS){
            if (resultCode == Activity.RESULT_OK){
                String estado = data.getStringExtra("ESTADO");
                Toast.makeText(this, "Aceptó terminos", Toast.LENGTH_SHORT).show();
                chb_terminos.setChecked(true);
            }else{
                chb_terminos.setChecked(false);
                Toast.makeText(this, "No acepto terminos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
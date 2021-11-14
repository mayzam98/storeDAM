package com.example.storedam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class RegistroActivity extends AppCompatActivity {
    private CheckBox chb_terminos;
    private Button btn_terminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        chb_terminos = findViewById(R.id.chb_terminos);
        btn_terminar = findViewById(R.id.btn_terminar);

        chb_terminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btn_terminar.setEnabled(isChecked);
            }
        });

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Prevent CheckBox state from being toggled when link is clicked
                widget.cancelPendingInputEvents();
                // Do action for link text...
                Intent intent = new Intent(RegistroActivity.this, TerminosActivity.class);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                // Show links with underlines (optional)
                ds.setUnderlineText(true);
            }
        };

        SpannableString linkText = new SpannableString("TERMS OF USE");
        linkText.setSpan(clickableSpan, 0, linkText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        CharSequence cs = TextUtils.expandTemplate(
                " ^1", linkText);

        chb_terminos.setText(cs);
// Finally, make links clickable
        chb_terminos.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
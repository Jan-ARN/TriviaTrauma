package com.codekobolde.triviatrauma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        // Button für den Login
        Button loginButton = findViewById(R.id.loginbestaetigung);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hier könntest du den Code für die tatsächliche Anmeldung hinzufügen
            }
        });

        // Button für "Noch kein Konto?" führt zur RegisterActivity
        Button noAccountButton = findViewById(R.id.alreadyHaveAccountButton);
        noAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starte die RegisterActivity beim Klick auf den Button
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}

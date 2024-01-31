package com.codekobolde.triviatrauma;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {


    private UserController usp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrierung);

        final EditText emailEditText = findViewById(R.id.loginemail);
        final EditText passwordEditText = findViewById(R.id.loginpasswort);
        Button registerButton = findViewById(R.id.loginbestaetigung);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // registrierrung.
                // Datenbankverbindung herstellen

                // Benutzerdaten speichern
                Toast.makeText(RegisterActivity.this, "Registrierung erfolgreich", Toast.LENGTH_SHORT).show();


                finish();
            }
        });

        // Button für "Doch schon ein Konto?" führt zur LoginActivity
        Button alreadyHaveAccountButton = findViewById(R.id.alreadyHaveAccountButton);
        alreadyHaveAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starte die LoginActivity beim Klick auf den Button
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}



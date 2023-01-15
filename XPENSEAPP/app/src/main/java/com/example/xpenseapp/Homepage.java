package com.example.xpenseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity {
    private Button LOGOUT;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        LOGOUT = (Button) findViewById(R.id.logout);
        LOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Homepage.this, "Logged out!", Toast.LENGTH_SHORT).show();
                // Go back to the StartActivity
                startActivity(new Intent(Homepage.this, MainActivity.class));
            }
        });
    }
}
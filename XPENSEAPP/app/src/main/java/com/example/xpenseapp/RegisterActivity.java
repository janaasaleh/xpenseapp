package com.example.xpenseapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button register;
    private FirebaseAuth auth;
    private EditText confirmpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        email = findViewById(R.id.username2);
        password = findViewById(R.id.pass2);
        register = findViewById(R.id.signup);
        confirmpass= findViewById(R.id.conf);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_confpassword = confirmpass.getText().toString();

                if(!txt_password.equals(txt_confpassword)){
                    Toast.makeText(RegisterActivity.this, "Password is not the same!", Toast.LENGTH_SHORT).show();

                }else {
                    // Check if name or password are not entered
                    if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                        Toast.makeText(RegisterActivity.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                    }
                    // Check if password is too short!
                    else if (txt_password.length() < 6) {
                        Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                    } else {
                        registerUser(txt_email, txt_password);
                    }
                }
            }
        });
    }

    private void registerUser(String txt_email, String txt_password) {
        // addOnCompleteListener --> to show a message upon completion!
        auth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Registering user successful!", Toast.LENGTH_SHORT).show();
                    // Forward the user to the MainActivity
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
package com.example.xpenseapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class addgoal extends AppCompatActivity{

    private EditText balanceamount;
    private EditText goaldescription;
    private EditText goalamount;
    private Button confirmgoal;



    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgoal);
        confirmgoal=(Button)findViewById(R.id.confirmbal);
        goaldescription=(EditText)findViewById(R.id.goaldescrip);
        goalamount=(EditText)findViewById(R.id.goalamount);
        goaldescription.setText("First Goal");
        goalamount.setText("0");
        String descriptuon= goaldescription.getText().toString();
        int goalamout=Integer.parseInt(String.valueOf(goalamount.getText()));


        confirmgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(addgoal.this,Homepage.class);
                iinent.putExtra("goalamount",goalamout);
                iinent.putExtra("goalesription",descriptuon);
                startActivity(iinent);
            }
        });


    }



}

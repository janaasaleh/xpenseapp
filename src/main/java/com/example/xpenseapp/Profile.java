package com.example.xpenseapp;

import static android.content.ContentValues.TAG;
import static com.example.xpenseapp.Keys.PREFS_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {
    TextView username;
    TextView firstname;
    TextView lastname;
    NavigationBarItemView home;
    NavigationBarItemView profile;
    NavigationBarItemView Calender;
    NavigationBarItemView Overview;
    NavigationBarItemView Wallet;
    TextView username1;
    TextView firstname1;
    TextView lastname1;
    TextView heymess;
    TextView userdisp;
    TextView exclamark;

    FirebaseFirestore db;
    FirebaseAuth auth;
    DocumentReference docRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        getDataFromDataBase();
        home = findViewById(R.id.navigation_home);
        profile = findViewById(R.id.navigation_profile);
        Overview = findViewById(R.id.Overview);
        Calender = findViewById(R.id.calender);
        Wallet = findViewById(R.id.wallet);
        //  lastname = (TextView) findViewById(R.id.last);
        firstname = (TextView) findViewById(R.id.first);
        //username=(TextView) findViewById(R.id.userprofile);

        // lastname1 = (TextView) findViewById(R.id.lastedit);
        firstname1 = (TextView) findViewById(R.id.firstedit);
        //username1=(TextView) findViewById(R.id.user);

        heymess = (TextView) findViewById(R.id.hey);
        userdisp = (TextView) findViewById(R.id.usernamedisp);
        exclamark=(TextView) findViewById(R.id.exclam);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(Profile.this,Homepage.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        Overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(Profile.this,OverviewActivity.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(Profile.this,Profile.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(Profile.this,CalenderActivity.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        Wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(Profile.this,wallet.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }

    public void getDataFromDataBase() {
        DocumentReference docRef = db.collection("Users").document(auth.getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData().values().toString());
                        firstname1.setText(document.getString("Name"));
                        userdisp.setText(document.getString("Name"));
                    }

                    else {
                        Log.d(TAG, "No such document");
                    }

                }
                else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }


}
package com.example.xpenseapp;



import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;

public class wallet extends AppCompatActivity {


    private static final String PREFS_KEY = " ";
    private Button addbal;
    private Button addsav;
    private Button clear;

    private TextView balance;
    private TextView savings;
    private int   standardamountbal;
    private int standardamountsav;
    private int toalsavings;
    private int totalbalance;
    static int counter1=0;
    FirebaseFirestore db;
    FirebaseAuth auth;
    private boolean balclicked;
    private boolean clicked;
    private int addedbalancet;
    private int addedsavingst;
    private int total;
    NavigationBarItemView home;
    NavigationBarItemView profile;
    NavigationBarItemView Calender;
    NavigationBarItemView Overview;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        getSupportActionBar().hide();

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        getDataFromDataBase();
        final SharedPreferences prefs = wallet.this.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        final int count = prefs.getInt(Keys.COUNT, 0);

        final SharedPreferences prefs1 = wallet.this.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        final int count2 = prefs.getInt(Keys.COUNT2, 0);



        addbal = (Button) findViewById(R.id.addbal);
        addsav = (Button) findViewById(R.id.addsav);
        addbal.setTextColor(Color.WHITE);
        addbal.setBackgroundColor(Color.BLACK);

        addsav.setTextColor(Color.WHITE);
        addsav.setBackgroundColor(Color.BLACK);
        home = findViewById(R.id.navigation_home);
        profile = findViewById(R.id.navigation_profile);
        Overview = findViewById(R.id.Overview);
        Calender = findViewById(R.id.calender);
        clear = (Button) findViewById(R.id.clear);


        clear.setTextColor(Color.WHITE);
        clear.setBackgroundColor(Color.BLACK);

        balance = (TextView) findViewById(R.id.bal);
        savings = (TextView) findViewById(R.id.save);

        balance.setText(String.valueOf(count));
        savings.setText(String.valueOf(count2));

        addsav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(wallet.this, addsavings.class);
                startActivity(inte);
            }
        });
        addbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(wallet.this, addbalance.class);
                startActivity(intent);
            }
        });

//        j@



        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              balance.setText("0");
              savings.setText("0");
             prefs1.edit().clear().apply();
              prefs.edit().clear().apply();

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(wallet.this,Homepage.class);
                iinent.putExtra("balancefinal", balance.getText().toString());
                iinent.putExtra("savingsfinal", savings.getText().toString());
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        Overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(wallet.this,OverviewActivity.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(wallet.this,Profile.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(wallet.this,CalenderActivity.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });




    }
    public void getDataFromDataBase()
    {
        DocumentReference docRef = db.collection("Users").document(auth.getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData().values().toString());
                            Log.i("dsad","Ahmed,I am here");
                            balance.setText(document.getString("Balance"));
                            savings.setText(document.getString("Savings"));

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }
}

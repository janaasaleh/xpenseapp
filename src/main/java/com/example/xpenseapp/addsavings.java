package com.example.xpenseapp;





import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
public class addsavings extends AppCompatActivity{

    private EditText savingsamount;
    private Button confirm;
    FirebaseFirestore db;
    FirebaseAuth auth;
    String Savings;
    String x;
    DocumentReference documentReference;
    @SuppressLint({"MissingInflatedId", "ResourceType"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsavings);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        getDataFromDataBase();
        confirm = (Button) findViewById(R.id.confirmsaving);
        confirm.setTextColor(Color.WHITE);
        confirm.setBackgroundColor(Color.BLACK);

        savingsamount = (EditText) findViewById(R.id.addamountsav);
        // balanceamount.setText(String.valueOf(1));
        // int amount=Integer.parseInt(String.valueOf(balanceamount.getText()));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x = savingsamount.getText().toString();
                x= String.valueOf(Integer.parseInt(x)+Integer.parseInt(Savings));
                addlistCus();
                addlistCus();
                Intent iinent = new Intent(addsavings.this, wallet.class);
                startActivity(iinent);
            }
        });
    }
        public void addlistCus()  {
            //initialize the hashMaps
            Map<String,Object> User1 = new HashMap<>();
           User1.put("Savings",x);
            String UserID= auth.getCurrentUser().getUid();
            documentReference = db.collection("Users").document(UserID);
            documentReference.update(User1).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    System.out.println("Ahmed, User is stored successfully");
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
                        Savings=document.getString("Savings");

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



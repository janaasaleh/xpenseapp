package com.example.xpenseapp;





import static android.content.ContentValues.TAG;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class addexp extends AppCompatActivity {

    private static final String PREFS_KEY = " ";
    private ListView lv1;

    private Button addBtn;
    private Button clear;

    String balance;
    private EditText itemEdt;
    private EditText amount;

    private listadapt adapter;
    private TextView total;
    private int pos;
    private ArrayList<String> lngList;
    private ArrayList<String> sub;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    NavigationBarItemView home;
    NavigationBarItemView profile;
    NavigationBarItemView Calender;
    NavigationBarItemView Wallet;
    NavigationBarItemView Overview;
    DocumentReference documentReference;



    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexp);

        db = FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        Intent i =getIntent();
        pos = i.getIntExtra("position",0);
        final SharedPreferences prefs = addexp.this.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);

        lv1 = findViewById(R.id.idLVLanguages);
        addBtn = findViewById(R.id.idBtnAdd);
        itemEdt = findViewById(R.id.idEdtItemName);

        addBtn.setTextColor(Color.WHITE);
        addBtn.setBackgroundColor(Color.BLACK);

        clear = findViewById(R.id.CLEAR);
        clear.setTextColor(Color.WHITE);
        clear.setBackgroundColor(Color.BLACK);

        amount = findViewById(R.id.amount);

        home = findViewById(R.id.navigation_home);
        profile = findViewById(R.id.navigation_profile);
        Overview = findViewById(R.id.Overview);
        Calender = findViewById(R.id.calender);
        Wallet = findViewById(R.id.wallet);

        total = findViewById(R.id.totalamount);

        lngList = new ArrayList<>();
        sub = new ArrayList<>();
        getDataFromDataBase(pos);
        //Intent i = getIntent();
//        lngList.add(i.getStringExtra("FoodName1"));
//        sub.add(i.getStringExtra("FoodAmount1"));
//
//        System.out.println("Ahmed,I am here!");
        adapter = new listadapt(this, lngList, sub);
        lv1.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = itemEdt.getText().toString();
                String subs = amount.getText().toString();
                String t = String.valueOf(total.getText()) ;
                int amount = Integer.parseInt(subs);
                int totalnew = amount + Integer.parseInt(t);
                balance = String.valueOf(Integer.parseInt(balance)-amount);
                System.out.print(totalnew);
                System.out.println("Ahmed");
                total.setText(String.valueOf(totalnew));
                lngList.add(item);
                sub.add(subs);
                adapter.notifyDataSetChanged();
                addlistCus2();

//                if (!(item.isEmpty() && subs.isEmpty())) {
//                    lngList.add(item);
//                    sub.add(subs);
//                    adapter.notifyDataSetChanged();
//
//                    int amount = Integer.parseInt(subs);
//                    int value = prefs.getInt(Keys.COUNT, 0);
//                    prefs.edit().putInt(Keys.COUNT, (value + amount)).apply();
//                    int refreshedValue = prefs.getInt(Keys.COUNT, 0);
//                    total.setText(String.valueOf(refreshedValue));
//                }
//                if (item.isEmpty() && !(subs.isEmpty())) {
//                    Context context = getApplicationContext();
//                    CharSequence text = "Please enter expense";
//                    int duration = Toast.LENGTH_SHORT;
//
//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.show();
//                }
//                if (subs.isEmpty() && !(item.isEmpty())) {
//                    Context context = getApplicationContext();
//                    CharSequence text = "Please enter amount";
//                    int duration = Toast.LENGTH_SHORT;
//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.show();
//
//                }
//                if (subs.isEmpty() && item.isEmpty()) {
//                    Context context = getApplicationContext();
//                    CharSequence text = "Fields empty!, Please Enter expense and amount";
//                    int duration = Toast.LENGTH_SHORT;
//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.show();
//                }

            }
        });

        Overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(addexp.this,OverviewActivity.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(addexp.this,Profile.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(addexp.this,CalenderActivity.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        Wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(addexp.this,wallet.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total.setText("0");
                itemEdt.setText("");
                amount.setText("");
                lngList.clear();
                sub.clear();
                prefs.edit().clear().apply();
                adapter.notifyDataSetChanged();
            }
        });
        lv1.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iinent = new Intent(addexp.this, addtoexp.class);
                startActivity(iinent);
            }

        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // addlist();
                addlistCus(pos);
                Intent iinent = new Intent(addexp.this, Homepage.class);
//                iinent.putExtra("totalExpenses", total.getText().toString());
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        int a = sh.getInt("total", 0);
//        total.setText(String.valueOf(a));
//
//        ArrayList<String> b = new ArrayList<>();
//        ArrayList<String> c = new ArrayList<>();
//        for (int i = 0; i < lngList.size(); i++) {
//            b.add(i, sh.getString("item", ""));
//            c.add(i, sh.getString("amount", ""));
//            lngList.add(b.get(i));
//            sub.add(c.get(i));
//            adapter.notifyDataSetChanged();
//        }
//
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//        myEdit.putInt("total", Integer.parseInt(total.getText().toString()));
//        for (int i = 0; i < lngList.size(); i++) {
//            myEdit.putString("item", lngList.get(i));
//            myEdit.putString("amount", sub.get(i));
//        }
//        myEdit.apply();
//
//
//    }
//    public void addlist()   {
//        //initialize the hashMaps
//        Map<String,Object> User = new HashMap<>();
//        User.put("FoodAmount1", sub.get(sub.size()-1));
//        User.put("FoodName1", lngList.get(lngList.size()-1));
//        String UserID= auth.getCurrentUser().getUid();
//        documentReference1 = db.collection("Users").document(UserID);
//        documentReference1.update(User).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                System.out.println("Ahmed, User is stored successfully");
//            }
//        });
//
//    }
    public void getDataFromDataBase(int pos) {
        DocumentReference docRef = db.collection("Users").document(auth.getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        switch (pos){
                            case 0:{total.setText(document.getString("ClothesExpenses"));break;}
                            case 1:{total.setText(document.getString("FoodExpenses"));break;}
                            case 2:{total.setText(document.getString("MedicationsExpenses"));break;}
                            case 3:{total.setText(document.getString("UtilitiesExpenses"));break;}
                            case 4:{total.setText(document.getString("OthersExpenses"));break;}

                        }
                        balance=document.getString("Balance");

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
    public void addlistCus(int x)   {
        //initialize the hashMaps
        Map<String,Object> User1 = new HashMap<>();
        switch (x){

            case 0:{User1.put("ClothesExpenses",total.getText());break;}
            case 1:{User1.put("FoodExpenses",total.getText());break;}
            case 2:{User1.put("MedicationsExpenses",total.getText());break;}
            case 3:{User1.put("UtilitiesExpenses",total.getText());break;}
            case 4:{User1.put("OthersExpenses",total.getText());break;}
        }
        String UserID= auth.getCurrentUser().getUid();
        documentReference = db.collection("Users").document(UserID);
        documentReference.update(User1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                System.out.println("Ahmed, User is stored successfully");
            }
        });

    }
    public void addlistCus2()   {
        //initialize the hashMaps
        Map<String,Object> User1 = new HashMap<>();
        User1.put("Balance",balance);
        String UserID= auth.getCurrentUser().getUid();
        documentReference = db.collection("Users").document(UserID);
        documentReference.update(User1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                System.out.println("Ahmed, User is stored successfully");
            }
        });

    }
}

//       Log.d(TAG, "DocumentSnapshot data: " + document.getData().values().toString());
//
////                            FoodAmount1 = document.getString("FoodAmount1");
////                            FoodName1 = document.getString("FoodName1");
//               lngList.add( document.getString("FoodName1"));
//               sub.add(document.getString("FoodAmount1"));
//               System.out.println("Ahmed,I am here!");
//               adapter = new listadapt(addexp.this, lngList, sub);
//               lv1.setAdapter(adapter);
//
//               System.out.println("Ahmed,I am here!");


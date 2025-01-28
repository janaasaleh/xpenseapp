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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button register;
    private FirebaseAuth auth;
    private EditText confirmpass;
    private EditText FristName;
    private EditText secondName;
    private String userName;
    private FirebaseFirestore db;
    String UserID;
    Users newUser;
    DocumentReference documentReference1;
    DocumentReference documentReference2;
    DocumentReference documentReference3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
    // initialize all objects
        email = findViewById(R.id.username2);
        password = findViewById(R.id.pass2);
        register = findViewById(R.id.signup);
        confirmpass= findViewById(R.id.conf);
        FristName = findViewById(R.id.firstname);
        secondName = findViewById(R.id.lastname);
        auth = FirebaseAuth.getInstance();
        db =FirebaseFirestore.getInstance();

    //Functions
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_confpassword = confirmpass.getText().toString();
                String txt_firstName = FristName.getText().toString();
                String txt_secondName= secondName.getText().toString();
                userName = txt_firstName +" " +txt_secondName;
               // createUser(userdata,txt_firstName,txt_secondName);
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

    //Classes
    private void registerUser(String txt_email, String txt_password) {
        // addOnCompleteListener --> to show a message upon completion!
        auth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Registering user successful!", Toast.LENGTH_SHORT).show();
                    createUser();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void createUser()   {
    //initialize the hashMaps
    Map<String,Object> User = new HashMap<>();
    User.put("Name",userName);
    User.put("Balance", "0");
    User.put("Savings", "0");
    User.put("FoodExpenses", "0");
    User.put("ClothesExpenses", "0");
    User.put("MedicationsExpenses", "0");
    User.put("OthersExpenses", "0");
    User.put("TotalExpenses", "0");
    User.put("UtilitiesExpenses", "0");
    User.put("HouseAmountNeeded", "0");
    User.put("HouseAmountSaved", "0");
    User.put("CarAmountNeeded", "0");
    User.put("CarAmountSaved", "0");
    User.put("FoodAmount1","0");
    User.put("FoodName1", "0");
    User.put("ClothesAmount1","0");
    User.put("ClothesName1", "0");
    UserID=auth.getCurrentUser().getUid();
    documentReference1 = db.collection("Users").document(UserID);
    documentReference1.set(User).addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void unused) {
            System.out.println("Ahmed, User is stored successfully");
        }
    });

}
}
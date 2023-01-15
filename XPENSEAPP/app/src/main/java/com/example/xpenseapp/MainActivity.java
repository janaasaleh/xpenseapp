package com.example.xpenseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private TextView gotoSignup;
    private TextView guest;
    private CheckBox rememb;
    private SharedPreferences msharedpref;
    private SharedPreferences.Editor editor;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // bind them
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
        auth = FirebaseAuth.getInstance();
        gotoSignup =findViewById(R.id.tv_signup);
        guest=findViewById(R.id.guest);
//        rememb=findViewById(R.id.checkBox);
//        msharedpref = PreferenceManager.getDefaultSharedPreferences(this);
//        editor=msharedpref.edit();

//        checkSharedPref();
        // Login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = username.getText().toString();
                String txt_password = password.getText().toString();
                // Check if name or password are not entered
                if (TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                    Toast.makeText(MainActivity.this, "Enter your email and password!", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(txt_email, txt_password);
                }
//                if(rememb.isChecked()){
//
//                    editor.putString(getString(R.string.checkbox),"True");
//                    String pass= password.getText().toString();
//                    editor.putString(getString(R.string.password),txt_password);
//                    String name= username.getText().toString();
//                    editor.putString(getString(R.string.username),txt_email);
//                    editor.apply();
////
//                }else{
//                    editor.putString(getString(R.string.checkbox),"False");
//                    editor.commit();
//                    editor.putString(getString(R.string.username),"");
//                    editor.commit();
//                    editor.putString(getString(R.string.password),"");
//                    editor.commit();
//                }



            }
        });
        gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Homepage.class));
            }
        });
    }
//    private void checkSharedPref(){
//        String checkbox=msharedpref.getString(getString(R.string.checkbox),"False");
//        String user=msharedpref.getString(getString(R.string.username),"");
//        String p=msharedpref.getString(getString(R.string.password),"");
//        username.setText(user);
//        password.setText(p);
//        if(checkbox.equals("True")){
//            rememb.setChecked(true);
//        }
//        else{
//            rememb.setChecked(false);
//        }
//
//
//
//    }
    private void loginUser(String txt_email, String txt_password) {

        auth.signInWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Homepage.class));
                // To prevent coming to this activity when the user presses back!
                finish();
            }else{
                    Toast.makeText(MainActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}



//    // Storing data into SharedPreferences
//    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
//
//    // Creating an Editor object to edit(write to the file)
//    SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//// Storing the key and its value as the data fetched from edittext
//myEdit.putString("name", name.getText().toString());
//        myEdit.putInt("age", Integer.parseInt(age.getText().toString()));
//
//// Once the changes have been made,
//// we need to commit to apply those changes made,
//// otherwise, it will throw an error
//        myEdit.commit();

    // Retrieving the value using its keys the file name
//// must be same in both saving and retrieving the data
//    SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
//
//    // The value will be default as empty string because for
//// the very first time when the app is opened, there is nothing to show
//    String s1 = sh.getString("name", "");
//    int a = sh.getInt("age", 0);
//
//// We can then use the data
//name.setText(s1);
//        age.setText(String.valueOf(a));




//    protected void onResume() {
//        super.onResume();
//
//        // Fetching the stored data
//        // from the SharedPreference
//        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//
//        String s1 = sh.getString("name", "");
//        int a = sh.getInt("age", 0);
//
//        // Setting the fetched data
//        // in the EditTexts
//        name.setText(s1);
//        age.setText(String.valueOf(a));
//    }
//
//    // Store the data in the SharedPreference
//    // in the onPause() method
//    // When the user closes the application
//    // onPause() will be called
//    // and data will be stored
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        // Creating a shared pref object
//        // with a file name "MySharedPref"
//        // in private mode
//        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//        // write all the data entered by the user in SharedPreference and apply
//        myEdit.putString("name", name.getText().toString());
//        myEdit.putInt("age", Integer.parseInt(age.getText().toString()));
//        myEdit.apply();
//    }
//}
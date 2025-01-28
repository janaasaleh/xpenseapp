package com.example.xpenseapp;







import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
public class addtoexp extends AppCompatActivity{

    private EditText addedmoney;
    private Button confirm;



    @SuppressLint({"MissingInflatedId", "ResourceType"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtoexp);

        confirm=(Button)findViewById(R.id.confirmaddition);

        confirm.setTextColor(Color.WHITE);
        confirm.setBackgroundColor(Color.BLACK);
        addedmoney=(EditText) findViewById(R.id.addmon);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(addtoexp.this,addexp.class);
                iinent.putExtra("addedmoney", addedmoney.getText().toString());
                startActivity(iinent);
            }
        });


    }

}

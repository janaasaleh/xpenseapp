package com.example.xpenseapp;

import static android.content.ContentValues.TAG;

import static com.example.xpenseapp.Keys.PREFS_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.LinkedList;
import java.util.List;

public class Homepage extends AppCompatActivity implements RecyclerviewInterface {
    private List<Integer> mExampleList;
    private List<String>mExampleList2;
    private List<Integer> mExampleList1;


    private List<String>goalnamelist;

    private List<String>targetlist;
    private List<String>amountsavedlist;
    private List<String>money1list;
    private List<String>money2list;

    private int totalofallexp;

    private RecyclerView mRecyclerView;private RecyclerView mRecyclerView1;
    private homepageadapt adapter;private GoalAdabt adapter1;
    private String[]data ={"Clothes"," Food "," Medications","Utilities","OTHERS"};
    private int[]GridImages={R.drawable.cloth_black_white,R.drawable.f_b_w,R.drawable.health_black_white,R.drawable.utilities_black_white,R.drawable.other};

    private String[]goalname ={"GOAL 1","GOAL 2"};

    private String[]target ={"Target","Target"};
    private String[]money1 ={"20000","1000000"};
    private String[]money2 ={"1000000","2000000"};
    private String[]amountsaved ={"Saved","Saved"};


    private int[]progress={23,75};
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager1;
    private int balancefinal,savingsfinal,totaloneexp,totaloneexpamount; String FoodAmount1="";String FoodName1="";
    //link all the widgets in the design file
    TextView totalmoney,balanceAmount,savingsAmount,HelloMsg;
    FloatingActionButton addexpense;
    NavigationBarItemView Wallet;NavigationBarItemView Profile;NavigationBarItemView Calender;NavigationBarItemView Overview;
    //Document reference
    static int counter =0;
    FirebaseFirestore db; String UserName;
    FirebaseAuth auth;
    DocumentReference docRef;
    //int valueTest = 11111;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        counter++;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        getDataFromDataBase();
        mRecyclerView = (RecyclerView) findViewById(R.id.recview);
        mRecyclerView1= (RecyclerView) findViewById(R.id.recview2);
        getSupportActionBar().hide();
        createExampleList();
        buildRecyclerView();
        totalmoney=findViewById(R.id.textView10);balanceAmount=findViewById(R.id.textView14);savingsAmount=findViewById(R.id.textView15); HelloMsg = findViewById(R.id.username);
        final SharedPreferences prefs = Homepage.this.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        final SharedPreferences prefs1 = Homepage.this.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        final SharedPreferences pr = Homepage.this.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        /////change
        Wallet =findViewById(R.id.wallet);
        Profile= findViewById(R.id.navigation_profile);
        Calender = findViewById(R.id.calender);
        Overview = findViewById(R.id.Overview);
        Intent intent = getIntent();
        if (intent.getStringExtra("balancefinal") != null) {
            try {
                balancefinal = Integer.parseInt(intent.getStringExtra("balancefinal"));
                int value1 = prefs.getInt(Keys.COUNT2, 0);
                prefs.edit().putInt(Keys.COUNT2, (balancefinal)).apply();
                int refreshedValue1 = prefs.getInt(Keys.COUNT2, 0);
                balanceAmount.setText(String.valueOf(refreshedValue1));
            } catch (NumberFormatException e) {
                balancefinal = 0;
            }
        }

        Intent x = getIntent();
        if (x.getStringExtra("savingsfinal") != null) {
            try {
                savingsfinal = Integer.parseInt(x.getStringExtra("savingsfinal"));
                int value2 = prefs1.getInt(Keys.COUNT3, 0);
                prefs1.edit().putInt(Keys.COUNT2, (savingsfinal)).apply();
                int refreshedValue2 = prefs1.getInt(Keys.COUNT2, 0);
                savingsAmount.setText(String.valueOf(refreshedValue2));
            } catch (NumberFormatException e) {
                savingsfinal = 0;
            }
        }
        Intent f = getIntent();
        if (f.getStringExtra("totalExpenses") != null) {
            try {
                totaloneexp=Integer.parseInt(f.getStringExtra("totalExpenses"));
                int value = pr.getInt(Keys.COUNT, 0);
                pr.edit().putInt(Keys.COUNT, (value+totaloneexp)).apply();
                int refreshedValue = prefs.getInt(Keys.COUNT, 0);
                totaloneexpamount=refreshedValue;
            } catch (NumberFormatException e) {
                totaloneexp = 0;
            }
        }

        Wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2= new Intent(Homepage.this,wallet.class);
                startActivity(intent2);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l= new Intent(Homepage.this,Profile.class);
                startActivity(l);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        Calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l= new Intent(Homepage.this,CalenderActivity.class);
                startActivity(l);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        Overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l= new Intent(Homepage.this,OverviewActivity.class);
                startActivity(l);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        pr.edit().putInt(Keys.COUNT, (totaloneexpamount)).apply();
        int totalexp = pr.getInt(Keys.COUNT, 0);
        totalmoney.setText(String.valueOf(totalexp));
        /////
    }
    //Create The List For The Recycle List
    public void createExampleList() {
        mExampleList = new LinkedList<>();
        for(int i=0;i< GridImages.length;i++)
        {
            mExampleList.add(GridImages[i]);
        }
        mExampleList2 = new LinkedList<>();
        for(int i=0;i< data.length;i++)
        {
            mExampleList2.add(data[i]);
        }
        mExampleList1 = new LinkedList<>();

        for(int i=0;i<progress.length;i++)
        {
            mExampleList1.add(progress[i]);
        }

        goalnamelist = new LinkedList<>();
        for(int i=0;i< goalname.length;i++)
        {
            goalnamelist.add(goalname[i]);
        }

        targetlist = new LinkedList<>();
        for(int i=0;i< target.length;i++)
        {
            targetlist.add(target[i]);
        }

        amountsavedlist = new LinkedList<>();
        for(int i=0;i< amountsaved.length;i++)
        {
            amountsavedlist.add(amountsaved[i]);
        }

        money1list = new LinkedList<>();
        for(int i=0;i< money1.length;i++)
        {
            money1list.add(money1[i]);
        }
        money2list = new LinkedList<>();
        for(int i=0;i< money2.length;i++)
        {
            money2list.add(money2[i]);
        }


    }
    //Building The View
    public void buildRecyclerView() {
        mRecyclerView1 = findViewById(R.id.recview2);
        mRecyclerView1.setHasFixedSize(true);
        mLayoutManager1 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView1.setLayoutManager(mLayoutManager1);
        adapter1= new GoalAdabt(goalnamelist,amountsavedlist,money1list,money2list,targetlist);
        mRecyclerView1.setAdapter(adapter1);
        ///
        mRecyclerView = findViewById(R.id.recview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter= new homepageadapt(mExampleList2,mExampleList,this);
        mRecyclerView.setAdapter(adapter);
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
                            HelloMsg.setText("Hello , "+document.getString("Name"));
                            balanceAmount.setText(document.getString("Balance"));
                            totalmoney.setText(String.valueOf(Integer.parseInt(document.getString("FoodExpenses"))+Integer.parseInt(document.getString("ClothesExpenses"))+Integer.parseInt(document.getString("MedicationsExpenses"))+Integer.parseInt(document.getString("OthersExpenses"))+Integer.parseInt(document.getString("UtilitiesExpenses"))));
                            savingsAmount.setText(document.getString("Savings"));


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt("total", Integer.parseInt(totalmoney.getText().toString()));
        myEdit.apply();
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int a = sh.getInt("total", 0);
        totalmoney.setText(String.valueOf(a));
    }
    @Override
    public void onItemClickListener(int position) {
        Intent f= new Intent(Homepage.this,addexp.class);
        f.putExtra("position", position);
        startActivity(f);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }





}
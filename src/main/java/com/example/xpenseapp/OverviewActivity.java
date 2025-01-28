package com.example.xpenseapp;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {
    private PieChart pieChart;
    ArrayList<PieEntry> entries = new ArrayList<>();
    FirebaseAuth auth;
    FirebaseFirestore db;
    private String[] categoryExpense = {"500","1500","500","200","300"};
    private final String[] categoryName = {"Food", "Clothes", "Medication", "Utilities", "Other"};
    private String totalExpense="3000";
    private float percentage;
    private TextView tip;
    NavigationBarItemView home;
    NavigationBarItemView profile;
    NavigationBarItemView Calender;
    NavigationBarItemView Overview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        home = findViewById(R.id.navigation_home);
        profile = findViewById(R.id.navigation_profile);
        Overview = findViewById(R.id.Overview);
        Calender = findViewById(R.id.calender);
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        getDataFromDataBase();

        pieChart = findViewById(R.id.overview);
        setupPieChart();
        loadPieChartData();
        tip = (TextView) findViewById(R.id.tip);
        tip.setText("Try reducing your expenses in " + categoryName[getMax(categoryExpense)] + ". That is your highest expense.");

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(OverviewActivity.this,Homepage.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        Overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(OverviewActivity.this,OverviewActivity.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(OverviewActivity.this,Profile.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iinent= new Intent(OverviewActivity.this,CalenderActivity.class);
                startActivity(iinent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Spending by Category");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }
    private void loadPieChartData() {

        for (int i=0; i<categoryName.length; i++){
            percentage = (float) Integer.parseInt(categoryExpense[i])/Integer.parseInt(totalExpense);
            entries.add(new PieEntry(percentage, categoryName[i]));
        }

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
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

                        categoryExpense[0] = (document.getString("FoodExpenses"));
                        categoryExpense[1] = (document.getString("MedicationsExpenses"));
                        categoryExpense[2] = (document.getString("ClothesExpenses"));
                        categoryExpense[3] = (document.getString("UtilitiesExpenses"));
                        categoryExpense[4] = (document.getString("OthersExpenses"));
                        totalExpense = (document.getString("TotalExpenses"));

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
    public int getMax(String[] categoryExpense){
        int maxAt = 0;

        for (int i = 0; i < categoryExpense.length; i++) {
            maxAt = Integer.parseInt(categoryExpense[i]) > Integer.parseInt(categoryExpense[maxAt]) ? i : maxAt;
        }
        return maxAt;
    }
}
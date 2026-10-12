package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class ReportsView extends AppCompatActivity {

    RecyclerView recyclerView;
    EMSdatabase dbopenhelper;
    List<ReportsModel> viewModelList;
    ReportsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reports_view);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_reports);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        viewModelList = new ArrayList<>();

        dbopenhelper = new EMSdatabase(this);
        viewModelList = dbopenhelper.getreportdata();

        adapter = new ReportsAdapter(this,viewModelList);
        recyclerView.setAdapter(adapter);
    }
}
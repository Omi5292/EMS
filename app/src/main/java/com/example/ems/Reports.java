package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Reports extends AppCompatActivity {

    EditText reporttype, description;
    Button adddetails;
    EMSdatabase mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reports);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        reporttype = (EditText) findViewById(R.id.reporttype);
        description = (EditText) findViewById(R.id.description);
        adddetails = findViewById(R.id.adddetails);

        mDatabaseHelper = new EMSdatabase(this);

        adddetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Entry1 = (reporttype != null && reporttype.getText() != null) ? reporttype.getText().toString() : "";
                String Entry2 = (description != null && description.getText() != null) ? description.getText().toString() : "";

                if (!Entry1.isEmpty() && !Entry2.isEmpty()){
                    AddData3(Entry1,Entry2);
                    if (reporttype != null) reporttype.setText("");
                    if (description != null) description.setText("");

                    Intent i = new Intent(Reports.this, EMSpanel.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Reports.this,"Plz enter the details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void AddData3(String Entry1,String Entry2){
        if (Entry1 == null || Entry2 == null) {
            Toast.makeText(Reports.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            return;
        }
        boolean insertData = mDatabaseHelper.addData3(Entry1,Entry2);
        if (insertData){
            Toast.makeText(Reports.this,"Details added successfully",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(Reports.this,"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
}
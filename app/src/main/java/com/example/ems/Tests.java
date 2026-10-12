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

public class Tests extends AppCompatActivity {

    EditText priortests, testtype, description;
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
        setContentView(R.layout.activity_tests);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        priortests = (EditText) findViewById(R.id.priortests);
        testtype = (EditText) findViewById(R.id.testtype);
        description = (EditText) findViewById(R.id.description);
        adddetails = findViewById(R.id.adddetails);

        mDatabaseHelper = new EMSdatabase(this);

        adddetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Entry1 = (priortests != null && priortests.getText() != null) ? priortests.getText().toString() : "";
                String Entry2 = (testtype != null && testtype.getText() != null) ? testtype.getText().toString() : "";
                String Entry3 = (description != null && description.getText() != null) ? description.getText().toString() : "";

                if (!Entry1.isEmpty() && !Entry2.isEmpty() && !Entry3.isEmpty()){
                    AddData2(Entry1,Entry2,Entry3);
                    if (priortests != null) priortests.setText("");
                    if (testtype != null) testtype.setText("");
                    if (description != null) description.setText("");

                    Intent i = new Intent(Tests.this, EMSpanel.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Tests.this,"Plz enter the details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void AddData2(String Entry1,String Entry2,String Entry3){
        if (Entry1 == null || Entry2 == null || Entry3 == null) {
            Toast.makeText(Tests.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            return;
        }
        boolean insertData = mDatabaseHelper.addData2(Entry1,Entry2,Entry3);
        if (insertData){
            Toast.makeText(Tests.this,"Details added successfully",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(Tests.this,"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
}
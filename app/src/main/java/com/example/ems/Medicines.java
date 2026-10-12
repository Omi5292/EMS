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

public class Medicines extends AppCompatActivity {

    EditText medname, medtype, description;
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
        setContentView(R.layout.activity_medicines);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        medname = (EditText) findViewById(R.id.medname);
        medtype = (EditText) findViewById(R.id.medtype);
        description = (EditText) findViewById(R.id.description);
        adddetails = findViewById(R.id.adddetails);

        mDatabaseHelper = new EMSdatabase(this);

        adddetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Entry1 = (medname != null && medname.getText() != null) ? medname.getText().toString() : "";
                String Entry2 = (medtype != null && medtype.getText() != null) ? medtype.getText().toString() : "";
                String Entry3 = (description != null && description.getText() != null) ? description.getText().toString() : "";

                if (!Entry1.isEmpty() && !Entry2.isEmpty() && !Entry3.isEmpty()){
                    AddData4(Entry1,Entry2,Entry3);
                    if (medname != null) medname.setText("");
                    if (medtype != null) medtype.setText("");
                    if (description != null) description.setText("");

                    Intent i = new Intent(Medicines.this, EMSpanel.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Medicines.this,"Plz enter the details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void AddData4(String Entry1,String Entry2,String Entry3){
        if (Entry1 == null || Entry2 == null || Entry3 == null) {
            Toast.makeText(Medicines.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            return;
        }
        boolean insertData = mDatabaseHelper.addData4(Entry1,Entry2,Entry3);
        if (insertData){
            Toast.makeText(Medicines.this,"Details added successfully",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(Medicines.this,"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
}
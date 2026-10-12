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

public class Registration extends AppCompatActivity {

    EditText name,mob,adharno,place,age,pass;
    Button register;
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
        setContentView(R.layout.activity_registration);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        name = (EditText) findViewById(R.id.editText1);
        mob = (EditText) findViewById(R.id.editText2);
        adharno = (EditText) findViewById(R.id.editText3);
        place = (EditText) findViewById(R.id.editText4);
        age = (EditText) findViewById(R.id.editText5);
        pass = (EditText) findViewById(R.id.editText6);
        register = findViewById(R.id.btnreg);

        mDatabaseHelper = new EMSdatabase(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameEntry = (name != null && name.getText() != null) ? name.getText().toString() : "";
                String mobEntry = (mob != null && mob.getText() != null) ? mob.getText().toString() : "";
                String adharnoEntry = (adharno != null && adharno.getText() != null) ? adharno.getText().toString() : "";
                String placeEntry = (place != null && place.getText() != null) ? place.getText().toString() : "";
                String ageEntry = (age != null && age.getText() != null) ? age.getText().toString() : "";
                String passEntry = (pass != null && pass.getText() != null) ? pass.getText().toString() : "";

                if (!nameEntry.isEmpty() && mobEntry.length() == 10 && adharnoEntry.length() == 12 && !placeEntry.isEmpty() && !ageEntry.isEmpty() && !passEntry.isEmpty()){
                    AddData(nameEntry,mobEntry,adharnoEntry,placeEntry,ageEntry,passEntry);
                    if (name != null) name.setText("");
                    if (mob != null) mob.setText("");
                    if (adharno != null) adharno.setText("");
                    if (place != null) place.setText("");
                    if (age != null) age.setText("");
                    if (pass != null) pass.setText("");
                } else {
                    Toast.makeText(Registration.this,"Plz enter valid credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void AddData(String nameEntry,String mobEntry,String adharnoEntry,String placeEntry,String ageEntry,String passEntry){
        if (nameEntry == null || mobEntry == null || adharnoEntry == null || placeEntry == null || ageEntry == null || passEntry == null) {
            Toast.makeText(Registration.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            return;
        }
        boolean insertData = mDatabaseHelper.addData(nameEntry,mobEntry,adharnoEntry,placeEntry,ageEntry,passEntry);
        if (insertData){
            Toast.makeText(Registration.this,"Registration successful 👍",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(Registration.this,"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }

    public void backtologin(View view) {
        Intent i = new Intent(Registration.this, MainActivity.class);
        startActivity(i);
    }
}
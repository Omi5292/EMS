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

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login;
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
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        username = findViewById(R.id.ed1);
        password = findViewById(R.id.ed2);
        login = findViewById(R.id.btnlogin);
        mDatabaseHelper = new EMSdatabase(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEntry = (username != null && username.getText() != null) ? username.getText().toString() : "";
                String passwordEntry = (password != null && password.getText() != null) ? password.getText().toString() : "";
                if (!userEntry.isEmpty() && !passwordEntry.isEmpty()){
                    valid(userEntry,passwordEntry);
                    if (username != null) username.setText("");
                    if (password != null) password.setText("");
                }else {
                    Toast.makeText(MainActivity.this,"Plz enter the details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void valid(String userEntry,String passwordEntry){
        if (userEntry == null || passwordEntry == null) {
            Toast.makeText(MainActivity.this,"Plz enter the details",Toast.LENGTH_SHORT).show();
            return;
        }
        boolean insertData = mDatabaseHelper.valid(userEntry,passwordEntry);
        if (insertData){
            Toast.makeText(MainActivity.this,"Welcome 👌",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, EMSpanel.class);
            startActivity(i);
        }else {
            Toast.makeText(MainActivity.this,"You have not registered yet !!",Toast.LENGTH_SHORT).show();
        }
    }
    //==Registration
    public void reg(View view) {
        Intent i = new Intent(MainActivity.this, Registration.class);
        startActivity(i);
    }
}
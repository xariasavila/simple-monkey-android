package com.example.simplemonkey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class AccountRegister extends AppCompatActivity {

    Button btnRegister;
    Spinner spinYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);

        // REFERENCIA
        btnRegister = findViewById(R.id.btnRegister);
        spinYear=(Spinner)findViewById(R.id.spinYear);

        // ARRAY LIST DEL SPINNER
        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1930; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);

        Spinner spinYear = (Spinner)findViewById(R.id.spinYear);
        spinYear.setAdapter(adapter);

        // REDIRECT A REGISTRO USUARIO
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ExpenseCreate.class);
                startActivity(intent);
            }
        });
    }
}
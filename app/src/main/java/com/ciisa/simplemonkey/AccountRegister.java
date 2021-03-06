package com.ciisa.simplemonkey;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ciisa.simplemonkey.ui.GenderSelected;
import com.ciisa.simplemonkey.ui.DatePickerFragment;
import com.ciisa.simplemonkey.utils.InputValidator;
import com.google.android.material.textfield.TextInputLayout;

public class AccountRegister extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    private Button btnRegister;
    String strFirstname, strLastname, strEmail, strGender, strBirth, strPass, strRepass;
    private TextInputLayout tilFirstname, tilLastname, tilEmail, tilBirth, tilPassword, tilRePassword;
    private TextView tvTerms;
    private CheckBox cbTerms;
    private Spinner spnGender;
    int genderInt[] = {R.drawable.fem, R.drawable.masc};
    String[] genderString={"Femenino","Masculino"};


    private void showDatePickerDialog(final TextInputLayout til) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = String.format("%d-%02d-%02d", year, (month +1), day);
                til.getEditText().setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);

        // ESTE LINK TIENE CONFLICTOS
        // tvTerms.setMovementMethod(LinkMovementMethod.getInstance());

        // REFERENCIA
        btnRegister = findViewById(R.id.btnRegister);
        tilFirstname = findViewById(R.id.tilFirstname);
        tilLastname = findViewById(R.id.tilLastname);
        tilEmail = findViewById(R.id.tilEmail);
        tilBirth = findViewById(R.id.tilBirth);
        tilPassword = findViewById(R.id.tilPassword);
        tilRePassword = findViewById(R.id.tilRePassword);
        cbTerms = findViewById(R.id.cbTerms);
        spnGender= findViewById(R.id.spGender);
        tvTerms = findViewById(R.id.tvTerms); // link terminos y condiciones

        // Escucha e instancia el DatePicker
        tilBirth.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(tilBirth);
            }
        });

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spnGender.setOnItemSelectedListener(this);
        GenderSelected customAdapter = new GenderSelected(getApplicationContext(), genderInt, genderString);
        spnGender.setAdapter(customAdapter);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strFirstname = tilFirstname.getEditText().getText().toString();
                strLastname = tilLastname.getEditText().getText().toString();
                strEmail = tilEmail.getEditText().getText().toString();
                strGender = tilEmail.getEditText().getText().toString();

                strBirth = tilBirth.getEditText().getText().toString();
                strPass = tilPassword.getEditText().getText().toString();
                strRepass = tilRePassword.getEditText().getText().toString();

                InputValidator inputValidator = new InputValidator(AccountRegister.this);

                inputValidator.isRequired(tilFirstname);
                inputValidator.isRequired(tilLastname);
                inputValidator.isEmail(tilEmail);
                inputValidator.isRequired(tilFirstname);
                inputValidator.isRequired(tilBirth);
                inputValidator.isRequired(tilPassword);
                inputValidator.isRequired(tilRePassword);
                inputValidator.isEqual(tilRePassword, tilPassword);

                if (inputValidator.validate()) {
                    if (cbTerms.isChecked()) {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AccountRegister.this, getString(R.string.error_terms), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}



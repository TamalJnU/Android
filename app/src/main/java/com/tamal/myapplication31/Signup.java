package com.tamal.myapplication31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText suETName;
    RadioGroup suETGender;
    CheckBox HSC;
    CheckBox BSS;
    CheckBox MSS;
    EditText suETEmail;
    EditText suETPassword;
    Button suBtnSignup;
    TextView stTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        suETName = findViewById(R.id.suName);
        suETGender = findViewById(R.id.empGender);
        suETGender.clearCheck();
        HSC = findViewById(R.id.suHSC);
        BSS = findViewById(R.id.suBSS);
        MSS = findViewById(R.id.suMSS);
        suETEmail = findViewById(R.id.suEmail);
        suETPassword = findViewById(R.id.suPassword);
        suBtnSignup = findViewById(R.id.suBtnSignup2);
        stTv = findViewById(R.id.suHaveAccount);

        RadioButton genderradioButton;

        stTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });

        suBtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = suETName.getText().toString();

                String radioText = "";
                String checkboxText = "";
                int selectedId = suETGender.getCheckedRadioButtonId();
                if(selectedId==-1){
                    Toast.makeText(Signup.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    //                suETGender = findViewById(selectedId);

                    RadioButton genderButton = (RadioButton) suETGender.findViewById(selectedId);
                    radioText = genderButton.getText().toString();
                }

                if (HSC.isChecked()) {
                    checkboxText += " " + "HSC";
                }
                if (BSS.isChecked()) {
                    checkboxText += " " + "BSS";
                }
                if (MSS.isChecked()) {
                    checkboxText += " " + "MSS";
                }

//                String hsc = HSC.getText().toString();
//                String bss = BSS.getText().toString();
//                String mss = MSS.getText().toString();
                String email = suETEmail.getText().toString();
                //String password = suETPassword.getText().toString();
                Toast.makeText(getApplicationContext(), "Name: " + name +
                        " Gender : " + radioText + " Education: " + checkboxText +
                        " Email: " + email + " Password: " + "password", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
package com.tamal.myapplication31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText siETEmail;
    EditText siETPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        siETEmail = findViewById(R.id.siEmail);
        siETPassword = findViewById(R.id.siPassword);
        btn = findViewById(R.id.siButtonSignin);

        tv = findViewById(R.id.tvRegister);

        Database db = new Database(getApplicationContext(), "employeeDB", null, 1);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CreateEmployee.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = siETEmail.getText().toString();
                String password = siETPassword.getText().toString();
                Toast.makeText(getApplicationContext(), "User Email: " +
                        email + " Password: " + password, Toast.LENGTH_SHORT).show();

                startActivity(new Intent(Login.this, Home.class));

//                if (userName.length()==0 || password.length()==0){
//                    Toast.makeText(getApplicationContext(),"Please Fill All The Data Field.",Toast.LENGTH_SHORT).show();
//                }else {
//                    if (db.login(userName,password)==1){
//                        Toast.makeText(getApplicationContext(),"Login Success Tex.",Toast.LENGTH_SHORT).show();
//                        SharedPreferences preferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = preferences.edit();
//                        editor.putString("usernamr",userName);
//                        editor.commit();
//                        editor.apply();
//                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                    }else {
//                        Toast.makeText(getApplicationContext(),"Wrong Password and UserName..",Toast.LENGTH_SHORT).show();
//                    }
//                }

            }
        });


    }
}
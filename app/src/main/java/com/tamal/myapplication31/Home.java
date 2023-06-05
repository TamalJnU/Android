package com.tamal.myapplication31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    LinearLayout homeCreateEmp, homeListEmp;
    CardView cvHOmeCreate, cvHomeGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeCreateEmp = findViewById(R.id.homeCardCreate);
        homeListEmp = findViewById(R.id.homeCardList);

        cvHOmeCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Create Employee Card", Toast.LENGTH_SHORT).show();
            }
        });

        cvHomeGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Create Employee Card", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
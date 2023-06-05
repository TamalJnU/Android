package com.tamal.myapplication31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ListEmployee extends AppCompatActivity {

    ArrayList employeeList;
    Button btnCreateEmp;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employee);

        Database db = new Database(getApplicationContext(), "employee", null, 1);
        employeeList = new ArrayList<>();
        employeeList = db.getAllEmployees();

        btnCreateEmp = findViewById(R.id.btnEmpListCreateEmployee);

        btnCreateEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateEmployee.class));
            }
        });

        sa = new SimpleAdapter(this, employeeList, R.layout.emp_list,
                new String[]{"id", "name", "email", "address", "salary", "department"},
                new int[]{R.id.layoutEmpListId, R.id.layoutEmpListName, R.id.layoutEmpListEmail,
                R.id.layoutEmpListAddress, R.id.layoutEmpListSalary, R.id.layoutEmpListDepartment}
                ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ImageView editImg = v.findViewById(R.id.layoutEmpListEdit);
                ImageView deleteImg = v.findViewById(R.id.layoutEmpListDelete);

                editImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println(position);
                        HashMap<String, String> user = new HashMap<>();
                        try {
                            user = (HashMap<String, String>) employeeList.get(position);
                            System.out.println(employeeList.get(position));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(getApplicationContext(), EditEmployee.class);
                        intent.putExtra("id", user.get("id"));
                        intent.putExtra("name", user.get("name"));
                        intent.putExtra("gender", user.get("gender"));
                        intent.putExtra("email", user.get("email"));
                        intent.putExtra("address", user.get("address"));
                        intent.putExtra("salary", user.get("salary"));
                        intent.putExtra("department", user.get("department"));
                        startActivity(intent);
                    }
                });

                deleteImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> user = new HashMap<>();
                        user = (HashMap<String, String>) employeeList.get(position);
                        boolean deleted = db.deleteEmployee(Integer.parseInt(Objects.requireNonNull(user.get("id"))));
                        if(deleted) {
                            employeeList.remove(position);
                            notifyDataSetChanged();
                        }
                        String message = deleted ? "Successfully Deleted!!" : "Deletation Failed!!";
                        Toast.makeText(ListEmployee.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
                return v;
            }
        };

        ListView lv = findViewById(R.id.lvEmpList);
        lv.setAdapter(sa);

    }
}
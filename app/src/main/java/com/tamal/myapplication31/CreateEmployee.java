package com.tamal.myapplication31;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tamal.myapplication31.entity.Employee;

import java.util.Calendar;

public class CreateEmployee extends AppCompatActivity {

    EditText nameEmp;
    RadioGroup genderEmp;
    EditText emailEmp;
    EditText addressEmp;
    EditText salaryEmp;
    Spinner departmentEmp;
    EditText startingDateEmp;
    Button btnCreateEmp;
    DatePickerDialog datePicker;
    TextView listOfEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);

        nameEmp = findViewById(R.id.empName);
        genderEmp = findViewById(R.id.empGender);
        emailEmp = findViewById(R.id.empEmail);
        addressEmp = findViewById(R.id.empAddress);
        salaryEmp = findViewById(R.id.empSalary);
        departmentEmp = findViewById(R.id.spinnerEmpDepartment);
        startingDateEmp = findViewById(R.id.empStartDate);
        listOfEmp = findViewById(R.id.tvListOfEmp);

        btnCreateEmp = findViewById(R.id.empUpdateBtn);

        Database db = new Database(getApplicationContext(), "employee", null, 1);

        btnCreateEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee emp = new Employee();
                String name = nameEmp.getText().toString();

                String radioText = "";
                int selectedId = genderEmp.getCheckedRadioButtonId();
                if(selectedId==-1){
                    Toast.makeText(CreateEmployee.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    //                suETGender = findViewById(selectedId);

                    RadioButton genderButton = (RadioButton) genderEmp.findViewById(selectedId);
                    radioText = genderButton.getText().toString();
                }

                String email = emailEmp.getText().toString();
                String address = addressEmp.getText().toString();
                String salary = salaryEmp.getText().toString();
                String department = departmentEmp.getSelectedItem().toString();
                String startDate = startingDateEmp.getText().toString();

                Toast.makeText(getApplicationContext(), "Name: " + name + " Gender: " + radioText +
                        " Email: " + email + " Address: " + address + " Salary: " + salary +
                        " Department: " + department + " Start Date:" + startDate, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateEmployee.this, Login.class);
                startActivity(intent);

                emp.setName(name);
                emp.setGender(radioText);
                emp.setEmail(email);
                emp.setAddress(address);
                emp.setSalary(Integer.valueOf(salary));
                emp.setDepartment(department);
                emp.setStartDate(startDate);

                db.addNewEmployee(emp);
            }
        });

        startingDateEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePicker = new DatePickerDialog(CreateEmployee.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                startingDateEmp.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePicker.show();
            }
        });

        listOfEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListEmployee.class);
                startActivity(intent);
            }
        });

    }
}
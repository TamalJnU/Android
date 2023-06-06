package com.tamal.myapplication31;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class EditEmployee extends AppCompatActivity {

    EditText idUpdateEmp;
    EditText nameUpdateEmp;
    RadioGroup genderUpdateEmp;
    EditText emailUpdateEmp;
    EditText addressUpdateEmp;
    EditText salaryUpdateEmp;
    Spinner departmentUpdateEmp;
    EditText startingDateUpdateEmp;

    Button btnUpdateEmp;
    TextView updateCancel;
    DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        idUpdateEmp = findViewById(R.id.empUpdateId);
        nameUpdateEmp = findViewById(R.id.empUpdateName);

//        String radioText = "";
//        int selectedId = genderUpdateEmp.getCheckedRadioButtonId();
//        if(selectedId==-1){
//            Toast.makeText(EditEmployee.this,"Nothing selected", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            //                suETGender = findViewById(selectedId);
//
//            RadioButton genderButton = (RadioButton) genderUpdateEmp.findViewById(selectedId);
//            radioText = genderButton.getText().toString();
//        }

        genderUpdateEmp = findViewById(R.id.empUpdateGender);
        emailUpdateEmp = findViewById(R.id.empUpdateEmail);
        addressUpdateEmp = findViewById(R.id.empUpdateAddress);
        salaryUpdateEmp = findViewById(R.id.empUpdateSalary);
        departmentUpdateEmp = findViewById(R.id.spinnerUpdateEmpDepartment);
        startingDateUpdateEmp = findViewById(R.id.emptartUpdateDate);

        btnUpdateEmp = findViewById(R.id.empUpdateBtn);
        updateCancel = findViewById(R.id.tvUpdateCancel);

        idUpdateEmp.setKeyListener(null);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String email = intent.getStringExtra("email");
        String address = intent.getStringExtra("address");
        String salary = intent.getStringExtra("salary");
        String department = intent.getStringExtra("department");
        String startdate = intent.getStringExtra("startdate");

        System.out.println(gender);
        idUpdateEmp.setText(id);
        nameUpdateEmp.setText(name);
        if(gender.contains("Male")){
            genderUpdateEmp.check(R.id.empUpdateMale);
        }else{
            genderUpdateEmp.check(R.id.empUpdateFemale);
        }
        emailUpdateEmp.setText(email);
        addressUpdateEmp.setText(address);
        salaryUpdateEmp.setText(salary);
//        Integer indexValue = MySpinner.getSelectedItemPosition();



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.departments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        departmentUpdateEmp.setAdapter(adapter);

        Integer pos;
        if(department.contains("Technical")) {
            pos = adapter.getPosition("Technical");
            departmentUpdateEmp.setSelection(pos);
        } else if (department.contains("Support")) {
            pos = adapter.getPosition("Support");
            departmentUpdateEmp.setSelection(pos);
        } else if (department.contains("Research and Development")) {
            pos = adapter.getPosition("Research and Development");
            departmentUpdateEmp.setSelection(pos);
        } else if (department.contains("Marketing")) {
            pos = adapter.getPosition("Marketing");
            departmentUpdateEmp.setSelection(pos);
        } else if (department.contains("Support")) {
            pos = adapter.getPosition("Support");
            departmentUpdateEmp.setSelection(pos);
        }else{
            departmentUpdateEmp.setSelection(0);
        }



        System.out.println("Date----------------startdate--"+startdate);

        if(startdate != null) {
            System.out.println("Date----------------startdate--"+startdate);
            startingDateUpdateEmp.setText(startdate);
        }

        Toast.makeText(this, "Name: " +name + " Gender: " + gender  + " Email: " + email
                + " Address: " + address + " Salary: " + salary + " Department: " + department
                + " Start Date: " + startdate, Toast.LENGTH_SHORT).show();
        Database db = new Database(getApplicationContext(), "employee", null,1);

        btnUpdateEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee emp = new Employee();
                Integer id = Integer.parseInt(idUpdateEmp.getText().toString());
                String name = nameUpdateEmp.getText().toString();
                String gender = String.valueOf(genderUpdateEmp.getCheckedRadioButtonId());
                String email = emailUpdateEmp.getText().toString();
                String address = addressUpdateEmp.getText().toString();
                String salary = salaryUpdateEmp.getText().toString();
                String department = (String) departmentUpdateEmp.getSelectedItem();
                String startdate = startingDateUpdateEmp.getText().toString();

                emp.setId(id);
                emp.setName(name);
                emp.setGender(gender);
                emp.setEmail(email);
                emp.setAddress(address);
                emp.setSalary(Integer.valueOf(salary));
                emp.setDepartment(department);
                emp.setStartDate(startdate);

                Boolean result = db.updateEmployee(emp);
                String message = result ? "Successfully Updated!" : "Updatation Failed!!";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();//
                Intent intent = new Intent(getApplicationContext(), ListEmployee.class);
                startActivity(intent);
            }
        });

        updateCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListEmployee.class);
                startActivity(intent);
            }
        });

        startingDateUpdateEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePicker = new DatePickerDialog(EditEmployee.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                startingDateUpdateEmp.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePicker.show();
            }
        });

    }
}
package com.tamal.myapplication31;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.tamal.myapplication31.entity.Employee;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public Database(@Nullable Context context, @Nullable String name, int version, @Nullable SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    private static final String dbName = "androiddb";
    private static final int dbVersion = 1;
    private static final String tracksColumn = "tracks";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryRegister = "create table employee (id integer primary key autoincrement, " +
                "name text, gender text, email text, address text, salary integer, department text, startdate text)";
        db.execSQL(queryRegister);
    }

    public void addNewEmployee(Employee emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", emp.getName());
        values.put("gender", emp.getGender());
        values.put("email", emp.getEmail());
        values.put("address", emp.getAddress());
        values.put("salary", emp.getSalary());
        values.put("department", emp.getDepartment());
        values.put("startdate", emp.getStartDate());

        db.insert("employee", null, values);
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAllEmployees() {
        HashMap<String, String> employee;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.rawQuery("select * from employee ", null);

        ArrayList<HashMap<String, String>> employeeList = new ArrayList<>(c.getCount());

        if (c.moveToFirst()) {

            do {
                employee = new HashMap<>();
                employee.put("id", c.getString(0));
                employee.put("name", c.getString(1));
                employee.put("gender", c.getString(2));
                employee.put("email", c.getString(3));
                employee.put("address", c.getString(4));
                employee.put("salary", c.getString(5));
                employee.put("department", c.getString(6));
                employee.put("startdate", c.getString(7));

                employeeList.add(employee);

            } while (c.moveToNext());

        }
        db.close();
        return employeeList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int login(String email, String password){

        int result = 0;
        String[] arr = new String[2];
        arr[0] = email;
        arr[1] = password;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from employee where name=? and password=? ", arr);
        if (c.moveToFirst()){
            return 1;
        }
        return 0;
    }

//    public ArrayList<HashMap<String ,String>> getEmployees() {
//        HashMap<String ,String> user;
//        SQLiteDatabase db = this.getWritableDatabase();
//        @SuppressLint("Recycle") Cursor c = db.rawQuery("select * from employee ", null);
//
//        ArrayList<HashMap<String ,String>> userList = new ArrayList<>(c.getCount());
//
//        if (c.moveToFirst()){
//
//            do {
//                user = new HashMap<>();
//                user.put("id",c.getString(0));
//                user.put("name",c.getString(1));
//                user.put("gender",c.getString(2));
//                user.put("education",c.getString(3));
//                user.put("email",c.getString(4));
//                user.put("password",c.getString(5));
//
//                userList.add(employee);
//
//            } while (c.moveToNext());
//
//        }
//        db.close();
//        return userList;
//    }

    public boolean updateEmployee(Employee emp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", emp.getName());
        values.put("email", emp.getEmail());
        values.put("address", emp.getAddress());
        values.put("salary", emp.getSalary());
        values.put("department", emp.getDepartment());
        int result = db.update("employee", values, "id = ?", new String[]{emp.getId() + ""});
        db.close();
        return result > 0;
    }

    public boolean deleteEmployee(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowCount = db.delete("employee", "id = ?", new String[]{id + ""});
        db.close();
        return rowCount > 0;
    }

}

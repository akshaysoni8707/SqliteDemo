package com.app.sqlitedemo.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.sqlitedemo.model.Employee;

import java.util.ArrayList;

public class EmployeeHandler extends SQLiteOpenHelper {

    public static final String DBNAME = "EmployeeDataBase.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "employee", ID = "id", NAME = "name", DESIGNATION = "designation", SALARY = "salary";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE + " (" +
                    ID + " INTEGER PRIMARY KEY," +
                    NAME + " TEXT," +
                    DESIGNATION + " TEXT," +
                    SALARY + " REAL)";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE;

    public EmployeeHandler(@Nullable Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);
    }

//    public EmployeeHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, DBNAME, null, DATABASE_VERSION);
//    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    //if id == 0 -> insert else update
    public boolean saveEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, employee.getName());
        contentValues.put(DESIGNATION, employee.getDesignation());
        contentValues.put(SALARY, employee.getSalary());

        return employee.getId() == 0 ? db.insert(TABLE, null, contentValues) > 0 :
                db.update(TABLE, contentValues, ID + " = ?", new String[]{String.valueOf(employee.getId())}) > 0;
    }

    public boolean deleteEmployee(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE, ID + " = ?", new String[]{String.valueOf(id)}) > 0;
    }

    public Employee getEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Employee employee = new Employee();
        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            employee.setId(cursor.getInt(0));
            employee.setName(cursor.getString(1));
            employee.setDesignation(cursor.getString(2));
            employee.setSalary(cursor.getDouble(3));
        }
        cursor.close();
        return employee;
    }

    public ArrayList<Employee> getAllEmployees() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
                Employee employee = new Employee();
                employee.setId(cursor.getInt(0));
                employee.setName(cursor.getString(1));
                employee.setDesignation(cursor.getString(2));
                employee.setSalary(cursor.getDouble(3));
                employees.add(employee);
            }
        cursor.close();
        return employees;
    }
}

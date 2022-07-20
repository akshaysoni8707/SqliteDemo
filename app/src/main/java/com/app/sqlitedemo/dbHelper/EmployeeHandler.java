package com.app.sqlitedemo.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.sqlitedemo.model.Employee;

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

    public EmployeeHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME, null, DATABASE_VERSION);
    }

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
}

package com.app.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.app.sqlitedemo.model.Employee;

public class ViewEmployeeActivity extends AppCompatActivity {
    TextView name,designation,salary,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        id = findViewById(R.id.employeeViewId);
        name = findViewById(R.id.employeeViewName);
        designation = findViewById(R.id.employeeViewDesignation);
        salary = findViewById(R.id.employeeViewSalary);

        Intent intent = getIntent();
        if(intent.getSerializableExtra("employee") != null){
            Employee employee = (Employee) intent.getSerializableExtra("employee");
            id.setText(String.valueOf(employee.getId()));
            name.setText(employee.getName());
            designation.setText(employee.getDesignation());
            salary.setText(String.valueOf(employee.getSalary()));
        }
    }
}
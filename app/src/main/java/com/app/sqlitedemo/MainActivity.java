package com.app.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.sqlitedemo.dbHelper.EmployeeHandler;
import com.app.sqlitedemo.model.Employee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView employeeRecyclerView;
    Context context;
    ArrayList<Employee> employees;
    EmployeeHandler employeeHandler;
    FloatingActionButton addEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        employeeHandler = new EmployeeHandler(context);
        employees = employeeHandler.getAllEmployees();

        employeeRecyclerView = findViewById(R.id.employeeRecyclerView);

        if(employees.size() > 0){
            employeeRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
            employeeRecyclerView.setAdapter(new EmployeeAdapter(context,employees));
        }

        addEmployee = findViewById(R.id.addEmployeeBtn);
        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FormActivity.class);
                startActivity(intent);
            }
        });

    }
}
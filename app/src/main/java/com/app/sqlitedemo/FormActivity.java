package com.app.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.sqlitedemo.dbHelper.EmployeeHandler;
import com.app.sqlitedemo.model.Employee;

public class FormActivity extends AppCompatActivity {
    Employee employee;
    EditText name,designation,salary;
    Button saveBtn;
    EmployeeHandler employeeHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        employeeHandler = new EmployeeHandler(this);
        name = findViewById(R.id.editTextEmployeeName);
        designation = findViewById(R.id.editTextEmployeeDesignation);
        salary = findViewById(R.id.editTextEmployeeSalary);
        saveBtn = findViewById(R.id.saveEmployee);

        Intent intent = getIntent();
        if(intent.getSerializableExtra("employee") != null){
            employee = (Employee) intent.getSerializableExtra("employee");
            name.setText(employee.getName());
            designation.setText(employee.getDesignation());
            salary.setText(String.valueOf(employee.getSalary()));
        }else{
            employee = new Employee();
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employee.setName(name.getText().toString());
                employee.setDesignation(designation.getText().toString());
                employee.setSalary(Double.parseDouble(salary.getText().toString()));
                employeeHandler.saveEmployee(employee);
                Intent intent1 = new Intent(FormActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });





    }
}
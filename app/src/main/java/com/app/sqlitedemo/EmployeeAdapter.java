package com.app.sqlitedemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.sqlitedemo.dbHelper.EmployeeHandler;
import com.app.sqlitedemo.model.Employee;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    ArrayList<Employee> employees;
    Context context;

    public EmployeeAdapter(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.employee_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = employees.get(position);

        if(employee != null){
            holder.name.setText(employee.getName());
            holder.designation.setText(employee.getDesignation());
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, FormActivity.class);
                    intent.putExtra("employee",employee);
                    context.startActivity(intent);
                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new EmployeeHandler(context).deleteEmployee(employee.getId());
                    employees.remove(employee);
                    notifyDataSetChanged();
                }
            });
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ViewEmployeeActivity.class);
                    intent.putExtra("employee",employee);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, designation;
        Button edit, delete;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.employeeNameTextView);
            designation = itemView.findViewById(R.id.employeeDesignationTextView);
            edit = itemView.findViewById(R.id.editEmployeeBtn);
            delete = itemView.findViewById(R.id.deleteEmployeeBtn);
            cardView = itemView.findViewById(R.id.employeeCardView);
        }
    }
}

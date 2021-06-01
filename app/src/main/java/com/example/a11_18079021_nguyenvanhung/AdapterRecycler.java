package com.example.a11_18079021_nguyenvanhung;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.EmployeeViewHolder> {

    private ArrayList<Employees> listEmployees;
    private onClickListener clickListener;


    public AdapterRecycler(ArrayList<Employees> listEmployees) {
        this.listEmployees = listEmployees;
    }

    public interface onClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClick(onClickListener mListener) {
        this.clickListener = mListener;
    }

    @Override
    public int getItemCount() {
        return listEmployees.size();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        EmployeeViewHolder evh = new EmployeeViewHolder(v,clickListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employees employees = listEmployees.get(position);

        holder.tvID.setText(String.valueOf(employees.getId()));
        holder.tvName.setText("Name: "+employees.getEmployeeName());
        holder.tvAge.setText("Age: " +String.valueOf(employees.getAge()));
        holder.tvDep.setText("Dep: "+employees.getDepartment());

    }
    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvID, tvName, tvAge, tvDep;
        private Button btnUpdate;

        public EmployeeViewHolder(@NonNull View itemView, onClickListener listener) {
            super(itemView);

            tvID = itemView.findViewById(R.id.tvID);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvDep = itemView.findViewById(R.id.tvDep);

            btnUpdate = itemView.findViewById(R.id.btnUpdate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

}

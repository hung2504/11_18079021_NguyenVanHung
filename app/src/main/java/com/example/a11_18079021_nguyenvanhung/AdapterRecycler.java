package com.example.a11_18079021_nguyenvanhung;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.EmployeeViewHolder> {

    private ArrayList<Employees> listEmployees;
    private onClickListener clickListener;
    private Context context;
    private int id;
    String url = "https://60b5dd31fe923b0017c84c7b.mockapi.io/employees";

    public AdapterRecycler(ArrayList<Employees> listEmployees, Context context) {
        this.listEmployees = listEmployees;
        this.context = context;
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
        private Button btnUpdate, btnDelete;

        public EmployeeViewHolder(@NonNull View itemView, onClickListener listener) {
            super(itemView);

            tvID = itemView.findViewById(R.id.tvID);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvDep = itemView.findViewById(R.id.tvDep);

            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                            id = position;
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
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Sure Delete Employee ?.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    DeleteApi(url);
                                    dialog.dismiss();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });
        }
    }
    private void DeleteApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}


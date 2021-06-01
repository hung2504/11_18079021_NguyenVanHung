package com.example.a11_18079021_nguyenvanhung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateEmployee extends AppCompatActivity {

    private EditText edtName, edtAge, edtDep;
    private Button btnSave, btnBack;
    private Employees e;
    private String url = "https://60b5dd31fe923b0017c84c7b.mockapi.io/employees";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        edtName = findViewById(R.id.edtNameUpdate);
        edtAge = findViewById(R.id.edtAgeUpdate);
        edtDep = findViewById(R.id.edtDepUpdate);

        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBackUpdate);

        Intent intent = getIntent();
        e = (Employees) intent.getSerializableExtra("emp");

        edtName.setText(e.getEmployeeName());
        edtAge.setText(String.valueOf(e.getAge()));
        edtDep.setText(e.getDepartment());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PutApi(url);
                Intent intent1 = new Intent(UpdateEmployee.this, ShowInfoRecyclerView.class);
                startActivity(intent1);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UpdateEmployee.this, ShowInfoRecyclerView.class);
                startActivity(intent1);
            }
        });

    }
    private void PutApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + '/' +(e.getId()), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(UpdateEmployee.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateEmployee.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("employeeName", edtName.getText().toString());
                params.put("age", edtAge.getText().toString());
                params.put("department", edtDep.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void DeleteApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + e.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(UpdateEmployee.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateEmployee.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
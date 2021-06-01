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

public class AddEmployee extends AppCompatActivity {

    String url = "https://60b5dd31fe923b0017c84c7b.mockapi.io/employees/";
    private EditText tvId, tvName,tvAge,tvDep;
    private Button btnSave,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        tvName = findViewById(R.id.edtNameAdd);
        tvAge = findViewById(R.id.edtAgeAdd);
        tvDep = findViewById(R.id.edtDepAdd);

        btnSave = findViewById(R.id.btnCreate);
        btnCancel = findViewById(R.id.btnBackAdd);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddEmployee.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostApi(url);
                Intent intent = new Intent(AddEmployee.this,ShowInfoRecyclerView.class);
                startActivity(intent);
            }
        });
    }
    private void PostApi(String url){
        StringRequest stringRequest = new StringRequest( Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddEmployee.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddEmployee.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("employeeName", tvName.getText().toString());
                params.put("age", tvAge.getText().toString());
                params.put("department", tvDep.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
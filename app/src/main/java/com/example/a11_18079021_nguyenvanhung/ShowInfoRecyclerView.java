package com.example.a11_18079021_nguyenvanhung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowInfoRecyclerView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterRecycler adapterRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Employees> list;
    private EditText tvSearch;
    private Button btnBack;
    String url = "https://60b5dd31fe923b0017c84c7b.mockapi.io/employees";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info_recycler_view);

        recyclerView = findViewById(R.id.recycler);
        btnBack = findViewById(R.id.btnBack);
        tvSearch = findViewById(R.id.edtSearch);
        list = new ArrayList<>();
        GetArrayJson(url);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapterRecycler = new AdapterRecycler(list,ShowInfoRecyclerView.this);
        recyclerView.setAdapter(adapterRecycler);

        adapterRecycler.setOnItemClick(new AdapterRecycler.onClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ShowInfoRecyclerView.this,UpdateEmployee.class);
                intent.putExtra("emp",list.get(position));
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowInfoRecyclerView.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetArrayJson(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<response.length(); i++){
                            try {
                                JSONObject object = (JSONObject) response.get(i);
                                int id = Integer.parseInt(object.getString("id"));
                                String name = object.getString("employeeName");
                                int age = object.getInt("age");
                                String dep = object.getString("department");
                                Employees employees = new Employees(id,name,age,dep);
                                Log.e("emp", id+"");
//                            Toast.makeText(MainActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
                                list.add(employees);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowInfoRecyclerView.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
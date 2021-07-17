package com.example.a8_18050261_hoanghuuhien;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowStudentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Student> listStudents = new ArrayList<>();
    String url = "https://60b75c0217d1dc0017b89c97.mockapi.io/students";
    private Button btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        getArrayJson(url);
        this.btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void getArrayJson(String url){
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(url,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for(int i=0; i<response.length(); i++){
                                    try {
                                        JSONObject object = (JSONObject) response.get(i);
                                        Student student = new Student();
                                        student.setId(object.getString("id"));
                                        student.setFullName(object.getString("fullName"));
                                        student.setClassName(object.getString("className"));
                                        student.setStatus(object.getString("status"));
                                        student.setWorkingName(object.getString("workingName"));

                                        listStudents.add(student);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                                adapter = new RecyclerAdapter(listStudents);
                                recyclerView.setAdapter(adapter);
                                layoutManager = new LinearLayoutManager(ShowStudentActivity.this,LinearLayoutManager.VERTICAL,false);
                                recyclerView.setLayoutManager(layoutManager);
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ShowStudentActivity.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}

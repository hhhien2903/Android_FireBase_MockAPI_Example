package com.example.a8_18050261_hoanghuuhien;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    private TextInputEditText inputName;
    private TextInputEditText inputClass;
    private TextInputEditText inputStatus;
    private TextInputEditText inputWorking;
    private Button btnCreate;
    private Button btnBack;
    String url = "https://60b75c0217d1dc0017b89c97.mockapi.io/students/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        this.inputName = (TextInputEditText) findViewById(R.id.inputName);
        this.inputClass = (TextInputEditText) findViewById(R.id.inputClass);
        this.inputStatus = (TextInputEditText) findViewById(R.id.inputStatus);
        this.inputWorking = (TextInputEditText) findViewById(R.id.inputWorking);
        this.btnCreate = (Button) findViewById(R.id.btnCreate);
        this.btnBack = (Button) findViewById(R.id.btnBack_add);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(inputName.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter Full Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(inputClass.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter Class!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(inputStatus.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter Status!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(inputWorking.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter Working!", Toast.LENGTH_SHORT).show();
                    return;
                }
                postApi(url);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void postApi(String url){

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddActivity.this, "Add New Student Successfully!", Toast.LENGTH_SHORT).show();
                        inputName.getText().clear();
                        inputClass.getText().clear();
                        inputStatus.getText().clear();
                        inputWorking.getText().clear();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String>
                        params = new HashMap<>();
                params.put("fullName", inputName.getText().toString());
                params.put("className", inputClass.getText().toString());
                params.put("status", inputStatus.getText().toString());
                params.put("workingName", inputWorking.getText().toString());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

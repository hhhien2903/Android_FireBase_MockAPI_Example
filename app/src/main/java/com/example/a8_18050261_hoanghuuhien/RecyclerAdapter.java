package com.example.a8_18050261_hoanghuuhien;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
    private List<Student> listStudents;
    String url = "https://60b75c0217d1dc0017b89c97.mockapi.io/students/";
    private Context context;
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(v);
        return recyclerViewHolder;
    }
    public RecyclerAdapter(List<Student> listStudents){
        this.listStudents = listStudents;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Student student = listStudents.get(position);
        holder.txtID.setText(student.getId());
        holder.txtFullName.setText("ST name: "+student.getFullName());
        holder.txtClass.setText("Class: "+ student.getClassName());
        holder.txtStatus.setText("Status: "+ student.getStatus());
        holder.txtWorking.setText("Working at: "+ student.getWorkingName());
        holder.student = student;
        holder.position = position;
    }


    @Override
    public int getItemCount() {
        return listStudents.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView txtID;
        public TextView txtFullName;
        public TextView txtClass;
        public TextView txtStatus;
        public TextView txtWorking;
        public int position;
        public Student student;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txtID);
            txtFullName = itemView.findViewById(R.id.txtFullName);
            txtClass = itemView.findViewById(R.id.txtClass);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtWorking = itemView.findViewById(R.id.txtWorking);
            itemView.findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context = v.getContext();
                    deleteApi(url, student);

                }
            });
            itemView.findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context = v.getContext();
                    Intent intent = new Intent(context,UpdateActivity.class);
                    intent.putExtra("student", student);
                    context.startActivity(intent);
                }
            });
        }
    }

    private void deleteApi(String url, Student student){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + student.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_SHORT).show();
                List<Student> listTemp = new ArrayList<Student>(listStudents);
                listTemp.remove(student);
                listStudents.clear();
                listStudents.addAll(listTemp);
                notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}

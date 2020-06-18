package com.trust.assesshealth.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trust.assesshealth.Model.Student;
import com.trust.assesshealth.Model.StudentAdapter;
import com.trust.assesshealth.R;

import java.util.ArrayList;

public class SelectStudentActivity extends AppCompatActivity
{

    private DatabaseReference ref;
    private RecyclerView recyclerView;
    private ArrayList<Student> list;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_student);

        recyclerView = findViewById(R.id.studentRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Student>();


        ref = FirebaseDatabase.getInstance().getReference().child("student");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
           {
               Student s = dataSnapshot1.getValue(Student.class);
               list.add(s);
           }
           adapter = new StudentAdapter(SelectStudentActivity.this,list);
           recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"An error occured!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
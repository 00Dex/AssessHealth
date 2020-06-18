package com.trust.assesshealth.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trust.assesshealth.Activity.MainActivity;
import com.trust.assesshealth.Model.User;
import com.trust.assesshealth.R;

public class RegistrationActivity extends AppCompatActivity
{
   private EditText rName,rSurname,rEmail,rPassword,rOrg,rIdNumber;
   private Button btnregister;
   private TextView rLogin;
   private DatabaseReference dr;
   private long maxID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dr = FirebaseDatabase.getInstance().getReference().child("user");
        rName = findViewById(R.id.erName);
        rSurname = findViewById(R.id.erSurname);
        rEmail = findViewById(R.id.erEmail);
        rIdNumber = findViewById(R.id.erIDNumber);
        rPassword = findViewById(R.id.erPassword);
        rOrg = findViewById(R.id.erOrg);
        rLogin = findViewById(R.id.tvLogin);

        rLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        btnregister = findViewById(R.id.btnRegister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String Name = rName.getText().toString().trim();
                String Surname = rSurname.getText().toString().trim();
                String Email = rEmail.getText().toString().trim();
                String Pass = rPassword.getText().toString().trim();
                String org = rOrg.getText().toString().trim();
                String idNum = rIdNumber.getText().toString().trim();
                User newU = new User(Name,Surname,Email,Pass,org,idNum);

                //dr.push().setValue(newU);
                dr.child(newU.getUserID()).setValue(newU);
                Toast.makeText(getApplicationContext(),"Successfully Registered!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });

    }
}
package com.trust.assesshealth.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.trust.assesshealth.Model.User;
import com.trust.assesshealth.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText loginName, loginPass;
    private Button bLogin;
    private TextView lRegister;
    private DatabaseReference ref;
    //private ArrayList<User> users;
    String userPswd;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //users = new ArrayList<User>();

        loginName = findViewById(R.id.eLoginName);
        loginPass = findViewById(R.id.eLoginPass);
        bLogin = findViewById(R.id.btnlogin);
        lRegister = findViewById(R.id.tvRegister);
        ref = FirebaseDatabase.getInstance().getReference("user");
        lRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });


        bLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                userName = loginName.getText().toString();
                userPswd = loginPass.getText().toString();
                if(isValid())
                {
                 Query chkUser = ref.orderByChild("userID").equalTo(userName);
                 chkUser.addListenerForSingleValueEvent(new ValueEventListener()
                 {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                     {
                         if(dataSnapshot.exists())
                         {
                             String pswd = dataSnapshot.child(userName).child("password").getValue(String.class);

                             if(pswd.equals(userPswd))
                             {
                                 String email = dataSnapshot.child(userName).child("email").getValue(String.class);;
                                 String name =  dataSnapshot.child(userName).child("name").getValue(String.class);;
                                 String surname = dataSnapshot.child(userName).child("surname").getValue(String.class);;
                                 String uID = dataSnapshot.child(userName).child("userID").getValue(String.class);;
                                 Toast.makeText(getApplicationContext(),"Login successful.",Toast.LENGTH_SHORT).show();
                                 Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                                 i.putExtra("email",email);
                                 i.putExtra("name",name);
                                 i.putExtra("surname",surname);
                                 i.putExtra("uID",uID);
                                 i.putExtra("passW",pswd);
                                 startActivity(i);
                             }else
                             {
                                 Toast.makeText(getApplicationContext(),"User doesnt exitst please register!",Toast.LENGTH_SHORT).show();
                             }
                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError)
                     {

                     }
                 });
                }

            }
        });
    }

    public Boolean isValid()
    {
        String val = loginName.getText().toString();
        String val2 = loginPass.getText().toString();

        if(val.isEmpty() || val2.isEmpty())
        {
            loginName.setError("Cannot be empty.");
            loginPass.setError("Cannot be empty.");
            return false;
        }else
        {
            loginName.setError(null);
            loginPass.setError(null);
            return true;
        }
    }
}
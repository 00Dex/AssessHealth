package com.trust.assesshealth.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.trust.assesshealth.Model.Kin;
import com.trust.assesshealth.Model.Student;
import com.trust.assesshealth.R;

public class AddActivity extends AppCompatActivity
{
    private EditText sName,sSurname,sNumber,sGender,kName,kSurname,kAdd,kPhnNo,kID;
    private Button btnSubmit,btnAcc;
    private DatabaseReference dr;
    private Student newStu;
    private Kin newKin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Init();

        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String Sname = sName.getText().toString().trim();
                String Ssurname = sSurname.getText().toString().trim();
                String Sgender = sGender.getText().toString().trim();
                String Snum = sNumber.getText().toString().trim();

                String KName = kName.getText().toString().trim();
                 String KSurname = kSurname.getText().toString().trim();
                 String KAdd = kAdd.getText().toString().trim();
                 String KID = kID.getText().toString().trim();
                 String phn = kPhnNo.getText().toString().trim();

                newKin = new Kin(KName,KSurname,KAdd,KID,phn);
                newStu = new Student(Sname,Ssurname,Sgender,Snum,newKin);

                addKin(newKin);
                addStudent(newStu);
                Toast.makeText(getApplicationContext(),"Successfully added!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });

        btnAcc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String Sname = sName.getText().toString().trim();
                String Ssurname = sSurname.getText().toString().trim();
                String Sgender = sGender.getText().toString().trim();
                String Snum = sNumber.getText().toString().trim();

                String KName = kName.getText().toString().trim();
                String KSurname = kSurname.getText().toString().trim();
                String KAdd = kAdd.getText().toString().trim();
                String KID = kID.getText().toString().trim();
                String phn = kPhnNo.getText().toString().trim();

                newKin = new Kin(KName,KSurname,KAdd,KID,phn);
                newStu = new Student(Sname,Ssurname,Sgender,Snum,newKin);

                Intent i = new Intent(AddActivity.this,AssessStudentActivity.class);
                i.putExtra("studentNumber",newStu.getStudentNum());
                i.putExtra("sName",newStu.getName());
                i.putExtra("sSurname",newStu.getSurname());
                i.putExtra("sGender",newStu.getGender());
                i.putExtra("kinName",newKin.getName());
                i.putExtra("kinSName",newKin.getSurname());
                i.putExtra("kinAddr",newKin.getAddress());
                i.putExtra("kinPhone",newKin.getPhoneNumber());

                addKin(newKin);
                addStudent(newStu);



                Log.d("APP-Assess:","Extra information parsed!");
                startActivity(i);
            }
        });

    }

    public void Init()
    {
        sName = findViewById(R.id.edStdName);
        sSurname = findViewById(R.id.edStdSurname);
        sNumber = findViewById(R.id.edStdNumber);
        sGender = findViewById(R.id.edStdGender);

        kName = findViewById(R.id.edKinName);
        kSurname = findViewById(R.id.edKinSurname);
        kAdd = findViewById(R.id.edKinAddr);
        kID = findViewById(R.id.edKID);
        kPhnNo = findViewById(R.id.edKinPhnNo);

        btnAcc = findViewById(R.id.btnHealthAcc);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    public void addKin(Kin k)
    {
        dr = FirebaseDatabase.getInstance().getReference().child("kin");
        dr.child(k.getID()).setValue(k);
        Log.d("APP","Added kin");
    }
    public void addStudent(Student s)
    {
        dr = FirebaseDatabase.getInstance().getReference().child("student");
        dr.child(s.getStudentNum()).setValue(s);
        Log.d("APP","Added student");
    }

}
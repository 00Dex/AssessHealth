package com.trust.assesshealth.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.trust.assesshealth.Model.Assessment;
import com.trust.assesshealth.Model.Health;
import com.trust.assesshealth.Model.Kin;
import com.trust.assesshealth.Model.Student;
import com.trust.assesshealth.Model.User;
import com.trust.assesshealth.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AssessStudentActivity extends AppCompatActivity
{
    private CheckBox dryCough,tiredness,shortBre;
    private Button bSubmit,bAlert;
    private EditText sTemperature,summary;
    private DatabaseReference dr;
    private String dryC,trms,shortnessB,tired,summ;
    private Health h;
    private Assessment a;
    private User u;
    private Kin kin;
    private int tempera;
    private Student student;
    private String hID;
    private final int HighTemp =37;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assess_student);
        Init();

        dryCough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
        if(dryCough.isChecked())
        {
            dryC = "Has a dry cough.";
        }else
        {
            dryC = "Does not present signs of a cough.";
        }
            }
        });

        tiredness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(tiredness.isChecked())
                {
                    tired = "Showing signs of fatigue.";
                }else
                {
                    tired = "Does not seem fatigued.";
                }
            }
        });

        shortBre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(shortBre.isChecked())
                {
                    shortnessB = "Has trouble breathing properly.";
                }else
                {
                    shortnessB = "Breathing is alright.";
                }
            }
        });

        bAlert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                createStudent();
                String kName = getIntent().getStringExtra("kinName");
                String kSurname = getIntent().getStringExtra("kinSName");
                String kAdds = getIntent().getStringExtra("kinAddr");
                String kPhne = getIntent().getStringExtra("kinPhone");

                Intent i = new Intent(getApplicationContext(),AlertActivity.class);
                i.putExtra("studentNumber",student.getStudentNum());
                i.putExtra("sName",student.getName());
                // i.putExtra("sSurname",student.getSurname());
               // i.putExtra("sGender",student.getGender());
                i.putExtra("kinName",kName);
                i.putExtra("kinSName",kSurname);
                i.putExtra("kinAddr",kAdds);
                i.putExtra("kinPhone",kPhne);
                trms = sTemperature.getText().toString().trim();

                Log.d("APP-Assess:","Extra information parsed!");
                hID = "SS + " + student.getStudentNum();
                h = new Health(hID,student,trms,dryC,tired,shortnessB,summ);
                addHealthRec(h);
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                a = new Assessment(u,h,date);
                addAssessment(a);
                startActivity(i);
            }
        });

        bSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dryCough.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        dryCough.toggle();
                        if(dryCough.isChecked())
                        {
                            dryC = "Has a dry cough.";
                        }else
                        {
                            dryC = "Does not present signs of a cough.";
                        }
                    }
                });

                tiredness.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        tiredness.toggle();
                        if(tiredness.isChecked())
                        {
                            tired = "Showing signs of fatigue.";
                        }else
                        {
                            tired = "Does not seem fatigued.";
                        }
                    }
                });

                shortBre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        shortBre.toggle();
                        if(shortBre.isChecked())
                        {
                            shortnessB = "Has trouble breathing properly.";
                        }else
                        {
                            shortnessB = "Breathing is alright.";
                        }
                    }
                });

                trms  = sTemperature.getText().toString().trim();
                summ = summary.getText().toString().trim();

                createStudent();
                hID = "SS + " + student.getStudentNum();
                h = new Health(hID,student,trms,dryC,tired,shortnessB,summ);

                addHealthRec(h);
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                a = new Assessment(u,h,date);
                addAssessment(a);
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });

    }

    public void Init()
    {
        dryCough = findViewById(R.id.CDrycough);
        shortBre = findViewById(R.id.CShortBre);
        tiredness = findViewById(R.id.CTiredness);
        sTemperature = findViewById(R.id.edTemperature);
        summary = findViewById(R.id.edSummary);



        bAlert = findViewById(R.id.btnAlertK);
        bSubmit = findViewById(R.id.btnSubmitReport);
    }

    public void createStudent ()
    {

        String tmpName = getIntent().getStringExtra("sName");
        String tmpStdnum = getIntent().getStringExtra("studentNumber");

        student = new Student(tmpName,tmpStdnum);
        Log.d("APP-Assess:","Student record created!");
    }

    public void addHealthRec(Health h)
    {
        dr = FirebaseDatabase.getInstance().getReference().child("healthRec");
        dr.child(h.getHealth_ID()).setValue(h);
        Toast.makeText(getApplicationContext(),"Added health record.",Toast.LENGTH_SHORT).show();
        Log.d("APP","Health record added!");
    }
    public  void addAssessment(Assessment a)
    {
        dr = FirebaseDatabase.getInstance().getReference().child("assessment");
        String dID = a.getH_ID().getHealth_ID();
        dr.child(dID).setValue(a);
        Toast.makeText(getApplicationContext(),"Added assesment.",Toast.LENGTH_SHORT).show();
        Log.d("APP","Assessment record added!");
    }
}
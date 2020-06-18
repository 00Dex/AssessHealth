package com.trust.assesshealth.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.trust.assesshealth.R;

public class HomeActivity extends AppCompatActivity
{
    private ImageView ivAssessStudent,ivAddStudent,ivAlertKin;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ivAssessStudent = findViewById(R.id.ivInspect);
        ivAddStudent =findViewById(R.id.ivAddStu);
        ivAlertKin = findViewById(R.id.ivAlert);


        ivAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),AddActivity.class));
            }
        });

        ivAssessStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SelectStudentActivity.class));
            }
        });

        ivAlertKin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SelectStudentActivity.class));
            }
        });
    }
}
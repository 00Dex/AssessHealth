package com.trust.assesshealth.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.trust.assesshealth.Model.Kin;
import com.trust.assesshealth.Model.Student;
import com.trust.assesshealth.R;

import java.security.acl.Permission;

public class AlertActivity extends AppCompatActivity
{
    private Button bAlertK,bAlertCdC;
    private TextView showText;
    private static final int REQUEST_CALL = 1;

    String kName ;
    String kSurname ;
    String kAddr ;
    String kPhn ;

    String stdNumber ;
    String stdName ;
   // String stdSurname ;
    // String stdGender ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        bAlertK = findViewById(R.id.btnKinAlert);
        bAlertCdC = findViewById(R.id.btnAlertCDC);
        showText = findViewById(R.id.tvAlertMessage);

         kName = getIntent().getStringExtra("KinName");
         kSurname = getIntent().getStringExtra("KinSName");
         kAddr = getIntent().getStringExtra("kinAddr");
         kPhn = getIntent().getStringExtra("kinPhone");

         stdNumber = getIntent().getStringExtra("StdNum");
         stdName = getIntent().getStringExtra("StdName");
         //stdSurname = getIntent().getStringExtra("StdSName");
         //stdGender = getIntent().getStringExtra("StdGen");

         //showText.setText("Student: "+ stdName + " " + stdSurname + "presents worrying symptoms. Advisable action to alert next of kin or legal guardian. Guardians name is : "+ kName+ " " +kSurname);
        bAlertK.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                makePhoneCall();
            }
        });

    }
    private void makePhoneCall()
    {
        String number = kPhn;
        if(number.trim().length() > 0)
        {
          Intent i = new Intent(Intent.ACTION_CALL);
          i.setData(Uri.parse("tel:"+number));
          if(ActivityCompat.checkSelfPermission(AlertActivity.this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
          {
              startActivity(i);
              startActivity(new Intent(getApplicationContext(),HomeActivity.class));
          }else {
              if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
                  new AlertDialog.Builder(AlertActivity.this)
                          .setTitle("Permission needed!")
                          .setMessage("In order to alert next of kin!")
                          .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  ActivityCompat.requestPermissions(AlertActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                              }
                          })
                          .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  dialog.dismiss();
                              }
                          }).create().show();
              }else
              {
                  ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
              }
          }

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please check phone number!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode == REQUEST_CALL)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                makePhoneCall();
            }
            else
                {
                    Toast.makeText(getApplicationContext(),"Permission denied!",Toast.LENGTH_SHORT).show();
                }
    }
}
}
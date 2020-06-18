package com.trust.assesshealth.Model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trust.assesshealth.Activity.AssessStudentActivity;
import com.trust.assesshealth.R;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.vHolder>
{
    Context context;
    ArrayList<Student> profile;
    public StudentAdapter(Context c, ArrayList<Student> p)
    {
        context = c;
        profile = p;
    }

    @NonNull
    @Override
    public vHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new vHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull vHolder holder, int position)
    {
        holder.name.setText(profile.get(position).getName());
        holder.studentNumber.setText(profile.get(position).getStudentNum());
    }

    @Override
    public int getItemCount()
    {
        return profile.size();
    }

    class vHolder extends RecyclerView.ViewHolder
    {
         TextView name,studentNumber;
         ImageView proPic;
         Button selectStudent;

        public vHolder (View itemView)
        {
            super(itemView);

            name =(TextView) itemView.findViewById(R.id.StudentName);
            studentNumber =(TextView) itemView.findViewById(R.id.StudentNo);
            proPic = (ImageView) itemView.findViewById(R.id.profilPic);
            selectStudent = (Button) itemView.findViewById(R.id.btnSelect);

            selectStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, AssessStudentActivity.class);
                        i.putExtra("sName",name.getText().toString().trim());
                        i.putExtra("studentNumber",studentNumber.getText().toString().trim());
                        context.startActivity(i);
                }
            });
        }
    }
}

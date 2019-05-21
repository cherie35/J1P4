package com.example.cherie.ohrapp;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class Graphs extends AppCompatActivity {

    DatabaseHelper db;

    private Spinner CoursePicker;
    private TextView teachernamecomments;
    private TextView N1,N2,N3,N4,N5,N6,N7,N8,N9;
    private Button Update;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        Intent intent = getIntent();
        String name = intent.getStringExtra(teacherMenu.TEACHER_NAME);

        //assigning to xml
        CoursePicker = (Spinner)findViewById(R.id.spnGraphCourse);
        teachernamecomments = (TextView)findViewById(R.id.tvGrphTeacherName);
        N1 = (TextView)findViewById(R.id.tvRNgoodexpl);
        N2 = (TextView)findViewById(R.id.tvRNpace);
        N3 = (TextView)findViewById(R.id.tvRNexamples);
        N4 = (TextView)findViewById(R.id.tvRNexercises);
        N5 = (TextView)findViewById(R.id.tvRNother);
        N6 = (TextView)findViewById(R.id.tvRNnotclear);
        N7 = (TextView)findViewById(R.id.tvRNengaging);
        N8 = (TextView)findViewById(R.id.tvRNfast);
        N9 = (TextView)findViewById(R.id.tvRNslow);


        Update =(Button)findViewById(R.id.btnUpdateGraph);
        db = new DatabaseHelper(this);

        teachernamecomments.setText(name);





        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String v1 = teachernamecomments.getText().toString();
                String v2 = CoursePicker.getSelectedItem().toString();
                //firstRow
                int r1 = db.countReasons(v1,v2,"The Explanation Was Thorough");
                String n1 = String.valueOf(r1);
                N1.setText(n1);

                int r2 = db.countReasons(v1,v2,"The Lecture/Class Was Properly Paced");
                String n2 = String.valueOf(r2);
                N2.setText(n2);

                int r3 = db.countReasons(v1,v2,"The Teacher Provided Useful Examples");
                String n3 = String.valueOf(r3);
                N3.setText(n3);

                int r4 = db.countReasons(v1,v2,"The Usage Of Additional Exercises");
                String n4 = String.valueOf(r4);
                N4.setText(n4);

                int r5 = db.countReasons(v1,v2,"The Lecture/Class Was Well Presented");
                String n5 = String.valueOf(r5);
                N5.setText(n5);

                /// secondRow
                int r6 = db.countReasons(v1,v2,"The Given Explanation Wasn\'t Clear");
                String new_amount = String.valueOf(r6);
                N6.setText(new_amount);

                int r7 = db.countReasons(v1,v2,"The Lecture/Class Wasn\'t Engaging Enough");
                String n7 = String.valueOf(r7);
                N7.setText(n7);

                int r8 = db.countReasons(v1,v2,"The Lecture/Class Was Too Fast-Paced");
                String n8 = String.valueOf(r8);
                N8.setText(n8);

                int r9 = db.countReasons(v1,v2,"The Lecture/Class Was Too Slow-Paced");
                String n9 = String.valueOf(r9);
                N9.setText(n9);

                Toast.makeText(getApplicationContext(), "Statistics Updated", Toast.LENGTH_SHORT).show();



            }
        });

    }





};

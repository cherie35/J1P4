package com.example.cherie.ohrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LesGoedSlecht extends AppCompatActivity {

    private Button BadGS;
    private Button GoodGS;



    //strings for passing to next activity
    public static final String TEACHER1 = "name_teacher";
    public static final String DATE1 = "date";
    public static final String COURSE1 = "course";
    // 1= Les goed/slecht
    // 2.1= Lijst pos, 2.2 = lijst neg
    // 3.1= andere pos, 3.2 = andere neg


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_les_goed_slecht);

        //getting CLASS CODE VALUES
        Intent intent = getIntent();
        final String teachername = intent.getStringExtra(EnterClassCode.TEACHER);
        final String course = intent.getStringExtra(EnterClassCode.COURSE);
        final String date = intent.getStringExtra(EnterClassCode.DATE);


        BadGS = (Button)findViewById(R.id.btnBadGoodBad);
        GoodGS = (Button)findViewById(R.id.btnGoodGoodBad);


        BadGS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LijstRedenenNeg.class);
                String name = teachername;
                String coursename = course;
                String thisdate = date;
                intent.putExtra(TEACHER1, name);
                intent.putExtra(COURSE1, coursename);
                intent.putExtra(DATE1, thisdate);
                startActivity(intent);
            }
        });
        GoodGS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = teachername;
                String coursename = course;
                String thisdate = date;
                Intent intent = new Intent(getApplicationContext(), LijstRedenenPos.class);
                intent.putExtra(TEACHER1, name);
                intent.putExtra(COURSE1, coursename);
                intent.putExtra(DATE1, thisdate);
                startActivity(intent);
            }
        });
    }
}

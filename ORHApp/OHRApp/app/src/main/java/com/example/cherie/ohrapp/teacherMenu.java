package com.example.cherie.ohrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class teacherMenu extends AppCompatActivity {
    private Button Stats;
    private Button CreateCC;
    private Button SignOut;
    public static final String TEACHER_NAME = "teacher_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_menu);

        Intent intent = getIntent();
        final String text = intent.getStringExtra(TeacherLogin.EXTRA_TEXT);

        Stats = (Button)findViewById(R.id.btnGraph);
        CreateCC = (Button)findViewById(R.id.btnCrtCLCD);
        SignOut = (Button)findViewById(R.id.btnSignOut);





        Stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = text;
                Intent intent = new Intent(teacherMenu.this, Graphs.class);
                intent.putExtra(TEACHER_NAME, name);
                startActivity(intent);
            }
        });


        CreateCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = text;
                Intent intent = new Intent(teacherMenu.this, CreateClassCode.class);
                intent.putExtra(TEACHER_NAME, name);
                startActivity(intent);
            }
        });
        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teacherMenu.this, HomeScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Exit", true);
                startActivity(intent);
            }
        });
    }


}

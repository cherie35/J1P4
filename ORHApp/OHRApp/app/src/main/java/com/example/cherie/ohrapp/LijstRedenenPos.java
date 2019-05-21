package com.example.cherie.ohrapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LijstRedenenPos extends AppCompatActivity {

    DatabaseHelper db;
    private Button SubmitLRP;
    public Spinner ospiner;
    private TextView name;
    private TextView classdate;
    private TextView course;




    // strings that will be passed to next activity
    public static final String TEACHER2_1 = "name_teacher";
    public static final String DATE2_1 = "date";
    public static final String COURSE2_1 = "course";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lijst_redenen_pos);

        db = new DatabaseHelper(this);





        //getting CLASS CODE VALUES
        Intent intent = getIntent();
        final String teacher_name = intent.getStringExtra(LesGoedSlecht.TEACHER1);
        final String course_name = intent.getStringExtra(LesGoedSlecht.COURSE1);
        final String date_ = intent.getStringExtra(LesGoedSlecht.DATE1);





        /// assigning to xml
        SubmitLRP = (Button)findViewById(R.id.btnNextRedPos);
        ospiner = (Spinner)findViewById(R.id.spinnerPositive);
        name = (TextView)findViewById(R.id.tv1st);
        course = (TextView)findViewById(R.id.tv2nd);
        classdate = (TextView)findViewById(R.id.tv3rd);



        //set text to requested db values teacherName, courseName, date
        name.setText(teacher_name);
        course.setText(course_name);
        classdate.setText(date_);


        SubmitLRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n1 = name.getText().toString();
                String n2 = course.getText().toString();
                String n3 = ospiner.getSelectedItem().toString();
                if(n3.equals("Select A Reason")){
                    Toast.makeText(getApplicationContext(),"Please Select A Reason",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean insert = db.insertReason(n1,n2,n3);
                    if (insert==true){
                        Toast.makeText(getApplicationContext(),"Feedback Saved Succesfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), Verzonden.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("Exit", true);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Feedback not Saved", Toast.LENGTH_LONG).show();
                }
            }
        }
        });




    }
}

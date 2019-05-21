package com.example.cherie.ohrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.cherie.ohrapp.LesGoedSlecht.COURSE1;
import static com.example.cherie.ohrapp.LesGoedSlecht.DATE1;
import static com.example.cherie.ohrapp.LesGoedSlecht.TEACHER1;

public class LijstRedenenNeg extends AppCompatActivity {

    DatabaseHelper db;
    private Button SubmitLRN;
    private Spinner ReasonSelectedNeg;
    private TextView Teacher;
    private TextView Course;
    private TextView Date;




    // strings that will be passed to next activity
    public static final String TEACHER2_2 = "name_teacher";
    public static final String DATE2_2 = "date";
    public static final String COURSE2_2 = "course";
    // 1= Les goed/slecht
    // 2.1= Lijst pos, 2.2 = lijst neg
    // 3.1= andere pos, 3.2 = andere neg





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lijst_redenen_neg);



        ///get CLASS CODE VALUES
        Intent intent = getIntent();
        final String teachername = intent.getStringExtra(LesGoedSlecht.TEACHER1);
        final String coursename = intent.getStringExtra(LesGoedSlecht.COURSE1);
        final String classdate = intent.getStringExtra(LesGoedSlecht.DATE1);


        db = new DatabaseHelper(this);


        //assigning to xml
        SubmitLRN = (Button)findViewById(R.id.btnNextRedNeg);
        ReasonSelectedNeg = (Spinner)findViewById(R.id.spWhyNeg);
        Teacher = (TextView)findViewById(R.id.tvteach);
        Course = (TextView)findViewById(R.id.tvcrsneg);
        Date = (TextView)findViewById(R.id.tvdateneg);


        Teacher.setText(teachername);
        Course.setText(coursename);
        Date.setText(classdate);



        SubmitLRN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //values that go into db
                String n1 = Teacher.getText().toString();
                String n2 = Course.getText().toString();
                String n3 = ReasonSelectedNeg.getSelectedItem().toString();
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

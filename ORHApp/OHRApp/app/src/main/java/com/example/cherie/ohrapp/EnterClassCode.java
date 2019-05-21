package com.example.cherie.ohrapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnterClassCode extends AppCompatActivity {



    DatabaseHelper db;
    private Button VerifyECC;
    private EditText EnteredCode;


    // strings that will be passed to next activity
    public static final String TEACHER = "nameteacher";
    public static final String DATE = "date";
    public static final String COURSE = "namecourse";
    // 1= Les goed/slecht
    // 2.1= Lijst pos, 2.2 = lijst neg
    // 3.1= andere pos, 3.2 = andere neg

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_class_code);
        /// components



        db = new DatabaseHelper(this);
        EnteredCode = (EditText)findViewById(R.id.etEnteredCode);
        VerifyECC = (Button)findViewById(R.id.btnNextEntClaCod);



        ///checks entered class code
        VerifyECC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = EnteredCode.getText().toString();

                //check if there is input and whether code is valid (not null or <6 characters)
                if (code.equals("") || (code.length() <6)){
                    Toast.makeText(getApplicationContext(), "Code is Too Short", Toast.LENGTH_SHORT).show();
                }
                else{
                    //if true code is registred
                    Boolean chckcode = db.chckEnterCode(code);
                    if(chckcode == true){
                        Toast.makeText(getApplicationContext(), "Feedback Process Started", Toast.LENGTH_SHORT).show();
                        String coderequest = EnteredCode.getText().toString();

                        String teacher = db.getTeacherName(coderequest).toString();
                        String course = db.getCourse(coderequest).toString();
                        String date = db.getDate(coderequest).toString();



                        //removes brackets so filename will be valid
                        //info.setText(teacher.substring(1,(teacher.length()-1)));
                        String new_teacher = teacher.substring(1,(teacher.length()-1));
                        String new_course = course.substring(1,(course.length()-1));
                        String new_date = date.substring(1,(date.length()-1));


                        Intent intent = new Intent(getApplicationContext(), LesGoedSlecht.class);
                        intent.putExtra(TEACHER, new_teacher);
                        intent.putExtra(COURSE, new_course);
                        intent.putExtra(DATE, new_date);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("Exit", true);
                        startActivity(intent);

                    }
                    else {
                        if(chckcode == false){
                            Toast.makeText(getApplicationContext(), "This Code Doesn't Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });

    }
}

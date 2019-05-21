package com.example.cherie.ohrapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;


public class CreateClassCode extends AppCompatActivity {

    //stuff
    DatabaseHelper db;
    private TextView LoggedUser;
    private Button Create;
    private Button Generate;//
    private Spinner Class;
    private Spinner Course;//
    private TextView Code; //e3
    private TextView Date; //e2
    private DatePickerDialog.OnDateSetListener DateSetListener;// e2

    //real shit below
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class_code);
        //getting username
        Intent intent = getIntent();
        String name = intent.getStringExtra(teacherMenu.TEACHER_NAME);
        // attributes ref
        db = new DatabaseHelper(this);
        Class = (Spinner)findViewById(R.id.spnClassesCCCC);
        Course = (Spinner)findViewById(R.id.spnCoursesCCC);
        Create = (Button)findViewById(R.id.btnCreateCCC);
        Generate = (Button)findViewById(R.id.btnGenerateCode);
        Code = (TextView)findViewById(R.id.tvGenCode);
        Date = (TextView)findViewById(R.id.tvDateSelCCC);
        LoggedUser = (TextView)findViewById(R.id.tvTeacherNameCC);


        //sets tv to logged in user username
        LoggedUser.setText(name);

        //sets Create button visibility to invisible
        Create.setVisibility(View.INVISIBLE);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // datepicker for class code
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                //popup screen
                DatePickerDialog dialog = new DatePickerDialog(
                        CreateClassCode.this, android.R.style.Theme_Holo_Light_Dialog,
                        DateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                Date.setText(date);

            }
        };





        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///gets selected values
                String s1 = Course.getSelectedItem().toString();
                String s2 = Class.getSelectedItem().toString();
                String s3 = Date.getText().toString();
                String s4 = Code.getText().toString();
                String s5 = LoggedUser.getText().toString();
                ///check input
                if(s3.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter A Date",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(s4.equals("")){
                        Toast.makeText(getApplicationContext(), "Please Generate A Code", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //insert data in db
                        Boolean chckcode = db.chckCode(s4);
                        if(chckcode==true){
                            Boolean insert = db.insert(s1,s2,s3,s4,s5);
                            if(insert==true)
                                Toast.makeText(getApplicationContext(),"Code Registered Succesfully", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(),"Please Take A Screenshot To Remember the code",Toast.LENGTH_LONG).show();

                        }
                        else
                            Toast.makeText(getApplicationContext(), "Code Already Exists", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });



        Generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //generates code with a lenght of 6,
                // and sets create(upload to db) to visible
                Code.setText(generateString(6));
                Create.setVisibility(View.VISIBLE);

            }
        });

    }
    //random code generator
    private String generateString(int lenght){
        //builds code containing (un)capitalized letters and numbers
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < lenght; i++){
            char c = chars[random.nextInt(chars.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}

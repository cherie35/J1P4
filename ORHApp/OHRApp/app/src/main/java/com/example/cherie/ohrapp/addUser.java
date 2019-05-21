package com.example.cherie.ohrapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addUser extends AppCompatActivity {
    DatabaseHelper db;
    private EditText Username;
    private EditText Password;
    private Button Register;
    private Spinner Teacher;
    private Spinner Student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        db = new DatabaseHelper(this);
        Username = (EditText)findViewById(R.id.etUsernameAU);
        Password = (EditText)findViewById(R.id.etPasswordAU);
        Register = (Button)findViewById(R.id.btnCreateUser);
        Teacher = (Spinner)findViewById(R.id.spnTeacher);
        Student = (Spinner)findViewById(R.id.spnStudent);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    String s1 = Username.getText().toString();
                    String s2 = Password.getText().toString();
                    String s3 = Teacher.getSelectedItem().toString();
                    String s4 = Student.getSelectedItem().toString();
                    if (s3.equals(s4)) {
                        Toast.makeText(getApplicationContext(), "Please Select Unique User Type Values", Toast.LENGTH_SHORT).show();
                    }
                    else if (s1.equals("")||s2.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please Fill out all fields", Toast.LENGTH_SHORT).show();
                    }

                    else { Boolean chckUser = db.chckUser(s1);
                        if (chckUser == true) {
                            Boolean insert = db.insertLogin(s1, s2, s3, s4);
                            if (insert == true)
                                Toast.makeText(getApplicationContext(), "Account Registered Succesfully", Toast.LENGTH_SHORT).show();

                        }
                        else {Toast.makeText(getApplicationContext(), "Username Already In Use", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

        });

    }
}


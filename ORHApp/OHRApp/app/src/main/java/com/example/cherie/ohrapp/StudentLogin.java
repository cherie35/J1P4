package com.example.cherie.ohrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentLogin extends AppCompatActivity {

    DatabaseHelper db;

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Attempts;
    private int Counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        db = new DatabaseHelper(this);

        Name = (EditText)findViewById(R.id.etUsernameS);
        Password = (EditText)findViewById(R.id.etPasswordS);
        Login = (Button)findViewById(R.id.btnLoginS);
        Attempts = (TextView)findViewById(R.id.tvAttemptsS);

        Attempts.setText("Remaining attempts: 3");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate(Name.getText().toString(), Password.getText().toString());
                String username = Name.getText().toString();
                String password = Password.getText().toString();
                Boolean ChckStudent = db.Susernamepassword(username,password);
                if(ChckStudent==true){
                    Intent intent = new Intent(StudentLogin.this, studentMenu.class);
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Exit", true);
                    startActivity(intent);

                }

                else {
                    Toast.makeText(getApplicationContext(), "Wrong login Credentials", Toast.LENGTH_SHORT).show();
                    Counter--;

                    Attempts.setText("Remaining attempts: " + String.valueOf(Counter));

                    if(Counter == 0){
                        Login.setEnabled(false);
                    }
                }

            }
        });
    }


}

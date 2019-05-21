package com.example.cherie.ohrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherLogin extends AppCompatActivity {

    public static final String EXTRA_TEXT = "teacher_name";


    DatabaseHelper db;

    private EditText NameT;
    private EditText PasswordT;
    private TextView AttemptsT;
    private Button LoginT;
    private int CounterT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        db = new DatabaseHelper(this);

        NameT = (EditText)findViewById(R.id.etUsernameT);
        PasswordT = (EditText)findViewById(R.id.etPasswordT);
        AttemptsT = (TextView)findViewById(R.id.tvAttemptsT);
        LoginT = (Button)findViewById(R.id.btnLoginT);

        AttemptsT.setText("Remaining attempts: 3");



        LoginT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for testing w/o db
               // validateT(NameT.getText().toString(), PasswordT.getText().toString());

                String username = NameT.getText().toString();
                String password = PasswordT.getText().toString();
                Boolean Chckusernamepassword = db.Tusernamepassword(username,password);
                if(Chckusernamepassword==true){
                    EditText editText1 = (EditText)findViewById(R.id.etUsernameT);
                    String text = editText1.getText().toString();
                    Intent intent = new Intent(TeacherLogin.this, teacherMenu.class);
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                    intent.putExtra(EXTRA_TEXT, text);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Exit", true);
                    startActivity(intent);}

                else{
                    Toast.makeText(getApplicationContext(),"Wrong login Credentials", Toast.LENGTH_SHORT).show();
                    CounterT--;

                    AttemptsT.setText("Remaining attempts: " + String.valueOf(CounterT));

                    if(CounterT == 0){
                        LoginT.setEnabled(false);
                    }
                }


            }
        });

            }


}

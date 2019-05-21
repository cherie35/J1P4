package com.example.cherie.ohrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class adminLogin extends AppCompatActivity {

    private Button LoginA;
    private EditText UsernameA;
    private EditText PasswordA;
    private TextView AttemptsA;
    private int Counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        LoginA = (Button)findViewById(R.id.btnLoginAdmin);
        UsernameA = (EditText)findViewById(R.id.etAdminUsername);
        PasswordA = (EditText)findViewById(R.id.etAdminPass);
        AttemptsA = (TextView)findViewById(R.id.tvAdmAttempts);


        AttemptsA.setText("Remaining attempts: 3");

        LoginA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate(UsernameA.getText().toString(), PasswordA.getText().toString());

            }
        });
    }

    private void validate(String usernameA, String passwordA){
        if ((usernameA.equals("ADM")) && (passwordA.equals("123"))){
            Intent intent = new Intent(adminLogin.this, admin_menu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Exit", true);
            startActivity(intent);
        }else{
            Counter--;

            AttemptsA.setText("Remaining attempts: " + String.valueOf(Counter));

            if(Counter == 0){
                LoginA.setEnabled(false);
            }

        }
    }
}

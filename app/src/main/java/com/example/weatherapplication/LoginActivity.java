package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usernameLog, passwordLog;
    Button signinLog;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLog = (EditText) findViewById(R.id.usernameLog);
        passwordLog = (EditText) findViewById(R.id.passwordLog);

        signinLog = (Button) findViewById(R.id.btnsigninLog);

        DB = new DBHelper(this);

        signinLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernameLog.getText().toString();
                String pw = passwordLog.getText().toString();

                if(user.equals("") || pw.equals(""))
                    Toast.makeText(LoginActivity.this,"Please enter User name and Password !", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUser = DB.checkUser(user,pw);
                    if(checkUser==true){
                        Toast.makeText(LoginActivity.this,"Sign in successfull !", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"Invalid Cardentials !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, confirmpw;
    Button signup, signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmpw = (EditText) findViewById(R.id.repassword);

        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);

        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                String user = username.getText().toString();
                String pw = password.getText().toString();
                String cpw = confirmpw.getText().toString();

                if(user.equals("") || pw.equals("") || cpw.equals(""))
                    Toast.makeText(MainActivity.this,"Please fill all the fields!", Toast.LENGTH_SHORT).show();
                else{
                    if(pw.equals(cpw)){
                        Boolean check = DB.checkUserName(user);
                        if(check==false){
                            Boolean insert = DB.insertData(user,pw);
                            if(insert==true){
                                Toast.makeText(MainActivity.this,"Registered Successfully !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"Registration failed !", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"User already exist. Please sign in!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Password not matching !", Toast.LENGTH_SHORT).show();
                    }

                }
             }
         });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
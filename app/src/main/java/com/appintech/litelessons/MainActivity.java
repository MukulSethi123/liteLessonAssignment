package com.appintech.litelessons;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email, pwd;
    TextView regUser;
    Button loginBut;
    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbh = new DBHelper(this);
        email = findViewById(R.id.email_edt);
        pwd = findViewById(R.id.pwd_edt);
        regUser = findViewById(R.id.register_tv);
        loginBut = findViewById(R.id.login_but);


        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sEmail,sPwd;
                sEmail = email.getText().toString();
                sPwd = pwd.getText().toString();
                boolean mailExist = dbh.chkMail(sEmail);
                if(sEmail.equals("") || sPwd.equals(""))
                    Toast.makeText(MainActivity.this,"fields cannot be empty",Toast.LENGTH_SHORT).show();
                else {
                    try {
                        if (mailExist) {
                            boolean pwdVerify = dbh.chkMailPass(sEmail, sPwd);
                            if (pwdVerify) {
                                Toast.makeText(MainActivity.this, "welcome " + sEmail, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,DashBoard.class));
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            }
                            else{
                                Toast.makeText(MainActivity.this,"incorrect Password",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "login Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Log.e("error", e+"");
                    }
                }

            }
        });
    }

    public void openRegisterPage(View v){
        startActivity(new Intent(MainActivity.this,Register.class));
    }
}

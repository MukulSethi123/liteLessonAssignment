package com.appintech.litelessons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DBHelper dbh;
    EditText uNameEdt,pwdEdt,chkpwdEdt,emailEdt;
    String uName, pwd, chkpwd, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbh = new DBHelper(this);
        uNameEdt = findViewById(R.id.email_edt);
        pwdEdt = findViewById(R.id.pwd_edt);
        chkpwdEdt = findViewById(R.id.chk_pwd_edt);
        emailEdt = findViewById(R.id.email_edt);
    }

    public  void onRegister(View view) {
        uName = uNameEdt.getText().toString();
        pwd = pwdEdt.getText().toString();
        chkpwd = chkpwdEdt.getText().toString();
        email = emailEdt.getText().toString();

        if (uName.equals("") || pwd.equals("") || chkpwd.equals("") || email.equals("")) {
            Toast.makeText(this,"empty feilds",Toast.LENGTH_SHORT).show();
        } else {

            boolean usedEmail = dbh.chkMail(email);
            if (usedEmail == false) {

                if (pwd.equals(chkpwd)) {
                    boolean inserted = dbh.insertData(uName, pwd, email);
                    if (inserted) {
                        Toast.makeText(this, "inserted " + inserted, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));

                    } else
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "email exists", Toast.LENGTH_SHORT).show();
            }

        }
    }
}

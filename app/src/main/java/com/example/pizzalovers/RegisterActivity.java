package com.example.pizzalovers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText reg_username;
    EditText reg_password;
    EditText reg_confirm_password;
    Button register_login;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
       reg_username = (EditText) findViewById(R.id.edit_userName);
        reg_password= (EditText) findViewById(R.id.edit_password);
        reg_confirm_password= (EditText) findViewById(R.id.edit__cnf_password);
        register_login=(Button) findViewById(R.id.btn_reg_login);

        register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= reg_username.getText().toString().trim();
                String pwd = reg_password.getText().toString().trim();
                String cnf_pwd = reg_confirm_password.getText().toString().trim();

                if(pwd.equals(cnf_pwd)){
                    long value = db.addUser(user,pwd);
                    if(value>0) {

                       Toast.makeText(RegisterActivity.this, "You have registered", Toast.LENGTH_SHORT).show();
                        Intent movetologin = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(movetologin);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration Error..", Toast.LENGTH_SHORT).show();
                    }
                }
               else {
                    Toast.makeText(RegisterActivity.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

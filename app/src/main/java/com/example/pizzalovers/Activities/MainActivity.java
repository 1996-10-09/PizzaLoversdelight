package com.example.pizzalovers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizzalovers.DatabaseHelper;
import com.example.pizzalovers.R;


public class MainActivity extends AppCompatActivity {
    EditText Username;
    EditText password;
    Button login;
    Button sign;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        Username = (EditText) findViewById(R.id.edit_text_userName);
        password= (EditText) findViewById(R.id.edit_text_password);
        login=(Button) findViewById(R.id.btn_login);
        sign =(Button) findViewById(R.id.btn_signIn);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInintent= new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(signInintent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String user= Username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                Boolean res = db.checkUser(user,pwd);

                if(res==true){
                    Toast.makeText(MainActivity.this,"Successfully Logged In",Toast.LENGTH_SHORT).show();
                    Intent menuintent= new Intent(MainActivity.this, Menu.class);
                    startActivity(menuintent);

               }
                else {
                    Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_SHORT).show();

               }
           }
        });





    }


}

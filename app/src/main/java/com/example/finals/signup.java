package com.example.finals;


import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class signup extends AppCompatActivity {

    EditText emailValue, password, username;
    Button signupbtn;
    TextView tosigninscreen;
    DBhelper DB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        DB = new DBhelper(this);
        username = findViewById(R.id.username);
        emailValue = findViewById(R.id.email);
        password = findViewById(R.id.password);
        tosigninscreen = (TextView) findViewById(R.id.tosigninbtn);
        signupbtn = findViewById(R.id.signupbtn);
        ConstraintLayout root = findViewById(R.id.root);

        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inp.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return false;
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String email = emailValue.getText().toString();
                String pass =  password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
                    Toast.makeText(signup.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.length() < 6){
                        Toast.makeText(signup.this, "Password is too short!", Toast.LENGTH_SHORT).show();
                    }else{
                        Boolean checkIfExist = DB.checkUsername(user);
                        if(checkIfExist==true){
                            Toast.makeText(signup.this, "Username is already taken!", Toast.LENGTH_SHORT).show();
                        }else{
                            Boolean createUser = DB.createUser(user, email, pass);
                            if(createUser==true){
//                                Toast.makeText(signup.this, "Sign up success!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("username", user);
                                startActivity(intent);
                            }else{
                                Toast.makeText(signup.this, "Sign up failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            }
        });






        tosigninscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), signin.class);
                startActivity(intent);
            }
        });

    }


//    public void setTosigninscreen(Button tosigninscreen) {
//        this.tosigninscreen = tosigninscreen;
//    }
}
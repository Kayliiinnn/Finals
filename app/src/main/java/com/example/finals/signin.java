package com.example.finals;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.input.InputManager;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signin extends AppCompatActivity {

    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Window window = getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.setStatusBarContrastEnforced(true);
        }

        TextView tosignupscreen = findViewById(R.id.tosignupbtn);
        Button signin = findViewById(R.id.signinbtn);
        ConstraintLayout root = findViewById(R.id.rootView);

        DB = new DBhelper(this);


        tosignupscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
            }
        });

        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inp.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return false;
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameText = (EditText) findViewById(R.id.username);
                EditText passwordText = (EditText) findViewById(R.id.password);
                String username = String.valueOf(usernameText.getText());
                String password = String.valueOf(passwordText.getText());


                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(signin.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkIfExist = DB.checkUsername(username);
                    if(checkIfExist == false){
                        Toast.makeText(signin.this, "Username does't exist!", Toast.LENGTH_SHORT).show();
                    }else{
                        Boolean auth = DB.auth(username, password);
                        if(auth == false){
                            Toast.makeText(signin.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                        }else{
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

    }

}
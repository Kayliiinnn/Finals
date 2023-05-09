package com.example.finals;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String user = getIntent().getStringExtra("username");
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView email = (TextView) findViewById(R.id.emailhere);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView userText = findViewById(R.id.usernamehere);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button logout = findViewById(R.id.logout);

        DB = new DBhelper(this);

        String db = String.valueOf(DB.getEmail(user));

        userText.setText(user);
        email.setText(db.toString());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), signin.class);
                startActivity(intent);
            }
        });


    }
}
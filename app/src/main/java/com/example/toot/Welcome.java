package com.example.toot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    TextView Username, Email;
    Button start, Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Username = findViewById(R.id.user);
        Email = findViewById(R.id.email);
        start = findViewById(R.id.welcome);
        Edit = findViewById(R.id.edit);

        String name=getIntent().getStringExtra("keyname");
        String mail=getIntent().getStringExtra("keyemail");

        Username.setText(name);
        Email.setText(mail);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Welcome.this,Upgrade.class);
                startActivity(intent);
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Welcome.this,nav.class);
                startActivity(intent);
            }
        });

    }

}
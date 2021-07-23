package com.example.toot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Upgrade extends AppCompatActivity {
    EditText User,Email,Pass;
    Button Logout,upgrade;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);

        User=findViewById(R.id.user);
        Email=findViewById(R.id.email);
        Pass=findViewById(R.id.pass);
        Logout=findViewById(R.id.logout);
        upgrade=findViewById(R.id.edit);
        dbHelper=new DBHelper(this);


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Upgrade.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(Upgrade.this,Login.class);
                startActivity(intent);
            }
        });

        upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=User.getText().toString();
                String mail=Email.getText().toString();
                String pass=Pass.getText().toString();
                Boolean i=dbHelper.update(name,mail,pass);
                if (i==true)
                    Toast.makeText(Upgrade.this, "Successful", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Upgrade.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
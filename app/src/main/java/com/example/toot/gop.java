package com.example.toot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class gop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gop);
        EditText User,Email,Password;
        Button Insert,Delete,View,Logout;
        DBHelper dbHelper;


        User=findViewById(R.id.user);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.pass);


        Delete=findViewById(R.id.delete);
        View=findViewById(R.id.view);
        Logout=findViewById(R.id.logout);
        Insert=findViewById(R.id.insert);
        dbHelper=new DBHelper(this);

        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String user = User.getText().toString();
                String email = Email.getText().toString();
                String pass = Password.getText().toString();

                if(user.equals("")||email.equals("")||pass.equals(""))
                    Toast.makeText(gop.this, "Please Enter All the Fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean i = dbHelper.insertData(user,pass,email);
                    if (i == true) {


                        Toast.makeText(gop.this, "User Data Inserted", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(gop.this, "User Data Is Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String user = User.getText().toString();
                Boolean i=dbHelper.delete_data(user);
                if (i==true)
                    Toast.makeText(gop.this, "Successful", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(gop.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Cursor t=dbHelper.getinfo();
                if(t.getCount()==0)
                {
                    Toast.makeText(gop.this, "No Data Found", Toast.LENGTH_SHORT).show();

                }
                StringBuffer buffer= new StringBuffer();
                while (t.moveToNext())
                {
                    buffer.append("username::"+t.getString(1)+"\n");
                    buffer.append("password::"+t.getString(2)+"\n");
                    buffer.append("email::"+t.getString(3)+"\n\n\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(gop.this);
                builder.setCancelable(true);
                builder.setTitle("SignUp User Data");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Toast.makeText(gop.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(gop.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
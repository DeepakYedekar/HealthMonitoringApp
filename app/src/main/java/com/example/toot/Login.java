package com.example.toot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText Username,Password;
    Button login;
    TextView Nacc;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LinearLayout linearLayout=findViewById(R.id.root);
        AnimationDrawable animationDrawable=(AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        Username=findViewById(R.id.username);
        Password=findViewById(R.id.password);
        login=findViewById(R.id.logbtn);
        Nacc=findViewById(R.id.newacc);
        DB=new DBHelper(this);





        Nacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user =Username.getText().toString();
                String pass =Password.getText().toString();

                if (user.equals("")||pass.equals("")) {
                    Toast.makeText(Login.this, "Please Enter All The Fields", Toast.LENGTH_SHORT).show();

                }else if (user.equals("admin")||pass.equals("admin")) {
                    Toast.makeText(Login.this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, gop.class);
                    startActivity(intent);
                }else{
                    Boolean checkuserpassword =DB.checkusernamepassword(user,pass);
                    if (checkuserpassword==true){
                        Toast.makeText(Login.this, "sign In Successful", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(Login.this,Welcome.class);
                        intent.putExtra("keyname",user);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Not Registered", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
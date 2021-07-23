package com.example.toot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Register  extends AppCompatActivity {

    EditText Username, Email, Password, Repassword;
    Button Regi;
    TextView Already;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        LinearLayout linearLayout=findViewById(R.id.root);
        AnimationDrawable animationDrawable=(AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        Repassword= findViewById(R.id.repassword);
        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        Email = findViewById(R.id.email);
        Regi = findViewById(R.id.regibtn);
        Already = findViewById(R.id.link);
        DB =new DBHelper(this);


        Already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

        Regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Username.getText().toString();
                String email = Email.getText().toString();
                String pass = Password.getText().toString();
                String repass= Repassword.getText().toString();


                if(user.equals("")||email.equals("")||pass.equals("")) {
                    Toast.makeText(Register.this, "Please Enter All the Fields", Toast.LENGTH_SHORT).show();
                }else {
                    if (pass.equals(repass) && pass.matches( "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$")){
                        Boolean checkuser= DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert =DB.insertData(user,pass,email);
                            if(insert==true){
                                Toast.makeText(Register.this, "Registered Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Welcome.class);
                                intent.putExtra("keyname",user);
                                intent.putExtra("keyemail",email);
                                startActivity(intent);
                            }else {
                                Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(Register.this, "User Already exists! Please Login", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Register.this, "Password Should 8 Char long and have special characters", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}
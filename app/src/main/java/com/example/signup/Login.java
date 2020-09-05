package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button btn_login;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Passenger Login Page");

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass1);
        btn_login = (Button) findViewById(R.id.button);

        firebaseAuth = firebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = email.getText().toString().trim();
                String passwod = password.getText().toString().trim();

                if(TextUtils.isEmpty(mail))
                {
                    Toast.makeText(Login.this, "Please enter E-Mail Adress", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(passwod))
                {
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(mail, passwod)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    Toast.makeText(Login.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    Toast.makeText(Login.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });

            }
        });

    }

    public void btn_Registration(View view) {
        startActivity(new Intent(getApplicationContext(),Registration.class));
    }
}
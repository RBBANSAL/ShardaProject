package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration<RequestQueue> extends AppCompatActivity {

    EditText full_name,e_mail,password,cpassword;
    Button btn_register;
    FirebaseDatabase firebaseDatabase;
    RadioButton radioGenderMale,radioGenderFemale;
    DatabaseReference databaseReference ;
    FirebaseAuth firebaseAuth;
    String gender="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setTitle("Passenger Sign Up Form");

        full_name = (EditText) findViewById(R.id.fullname);
        e_mail = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        cpassword = (EditText) findViewById(R.id.cpass);
        btn_register = (Button) findViewById(R.id.button);
        radioGenderMale = (RadioButton) findViewById(R.id.male);
        radioGenderFemale = (RadioButton) findViewById(R.id.female);

        databaseReference = FirebaseDatabase.getInstance().getReference("passenger");
        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String f_name = full_name.getText().toString().trim();
                final String mail = e_mail.getText().toString().trim();
                final String passwod = password.getText().toString().trim();
                String ccpasswod = cpassword.getText().toString().trim();

                if (radioGenderMale.isChecked()) gender="Male";
                if(radioGenderFemale.isChecked()) gender="Female";



                if(TextUtils.isEmpty(mail))
                {
                    Toast.makeText(Registration.this, "Please enter E-Mail Adress", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(passwod))
                {
                    Toast.makeText(Registration.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(ccpasswod))
                {
                    Toast.makeText(Registration.this, "Please Enter Confirm-Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(passwod.length()<6)
                {
                    Toast.makeText(Registration.this, "Password have 6 letters", Toast.LENGTH_SHORT).show();
                }

                if(passwod.equals(ccpasswod))
                {
                    firebaseAuth.createUserWithEmailAndPassword(mail, passwod)
                            .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        passenger information = new passenger(
                                                f_name,
                                                mail,
                                                passwod,
                                                gender
                                        );

                                        FirebaseDatabase.getInstance().getReference("Passenger")
                                               .child(FirebaseAuth.getInstance().getUid())
                                               .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {

                                               Toast.makeText(Registration.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                           }
                                       });

                                    } else {
                                        Toast.makeText(Registration.this, "Falied", Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });
                }

            }
        });



    }

    public void btn_Login(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    public void btn_Registration(View view) {
        startActivity(new Intent(getApplicationContext(),Registration.class));
    }
}
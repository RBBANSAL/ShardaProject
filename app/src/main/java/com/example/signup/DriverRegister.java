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

public class DriverRegister<RequestQueue> extends AppCompatActivity {

    EditText dfull_name,de_mail,password,cpassword,carnum,model,locate,sys_id;
    Button btn_register;
    FirebaseDatabase firebaseDatabase;
    RadioButton radioGenderMale,radioGenderFemale;
    DatabaseReference databaseReference ;
    FirebaseAuth firebaseAuth;
    String gender="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_register);
        getSupportActionBar().setTitle("Driver Sign Up Form");

        dfull_name = (EditText) findViewById(R.id.dr_name);
        de_mail = (EditText) findViewById(R.id.dr_email);
        carnum = (EditText) findViewById(R.id.carnum);
        model = (EditText) findViewById(R.id.carmodel);
        locate = (EditText) findViewById(R.id.locate);
        sys_id = (EditText) findViewById(R.id.dr_ID);
        btn_register = (Button) findViewById(R.id.dr_register);
        radioGenderMale = (RadioButton) findViewById(R.id.dr_male);
        radioGenderFemale = (RadioButton) findViewById(R.id.dr_female);

        databaseReference = FirebaseDatabase.getInstance().getReference("driver");
        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fl_name = dfull_name.getText().toString().trim();
                final String mail = de_mail.getText().toString().trim();
                final String car_num = carnum.getText().toString().trim();
                final String car_model = model.getText().toString().trim();
                final String location = locate.getText().toString().trim();
                final String id = sys_id.getText().toString().trim();

                if (radioGenderMale.isChecked()) gender="Male";
                if(radioGenderFemale.isChecked()) gender="Female";



                if(TextUtils.isEmpty(mail))
                {
                    Toast.makeText(DriverRegister.this, "Please enter E-Mail Adress", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(car_num))
                {
                    Toast.makeText(DriverRegister.this, "Please Enter Car Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(car_model))
                {
                    Toast.makeText(DriverRegister.this, "Please Enter Car Model", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(id.length()!=10) {
                    Toast.makeText(DriverRegister.this, "Sharda ID have 10 numbers", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.createUserWithEmailAndPassword(mail, id)
                            .addOnCompleteListener(DriverRegister.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        driver information = new driver(
                                                fl_name,
                                                mail,
                                                car_num,
                                                car_model,
                                                location,
                                                id,
                                                gender

                                        );
                                        FirebaseDatabase.getInstance().getReference("Driver")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Toast.makeText(DriverRegister.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                            }
                                        });

                                    } else {
                                        Toast.makeText(DriverRegister.this, "Falied", Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });
                }

            }
        });



    }

}
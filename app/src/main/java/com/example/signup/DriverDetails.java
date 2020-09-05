package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverDetails extends AppCompatActivity {

    TextView a,b,c,d,e;
    Button btn_view;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);

        a = (TextView) findViewById(R.id.nameview);
        b = (TextView) findViewById(R.id.carname);
        c = (TextView) findViewById(R.id.locationview);
        d = (TextView) findViewById(R.id.genderview);
        e = (TextView) findViewById(R.id.sysidview);
        btn_view=  (Button) findViewById(R.id.buttonview);

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference =FirebaseDatabase.getInstance().getReference().child("Driver").child("iJr4KQ0VdBWS8SikGgAsKTih5Ei2");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String drname = dataSnapshot.child("Full_name").getValue().toString();
                        String drcarview = dataSnapshot.child("Car_num").getValue().toString();
                        String drlocationname = dataSnapshot.child("Location").getValue().toString();
                        String drgender = dataSnapshot.child("Gender").getValue().toString();
                        String drid_sys = dataSnapshot.child("System_id").getValue().toString();

                        a.setText(drname);
                        b.setText(drcarview);
                        c.setText(drlocationname);
                        d.setText(drgender);
                        e.setText(drid_sys);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
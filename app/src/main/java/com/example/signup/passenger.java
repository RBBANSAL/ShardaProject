package com.example.signup;

import android.widget.Button;
import android.widget.EditText;

public class passenger {
    public String Full_name,E_mail,Password,Gender;

    public passenger(){

    }

    public passenger(String full_name, String e_mail, String pass, String gender) {
        this.Full_name=full_name;
        E_mail=e_mail;
        Password=pass;
        Gender=gender;
    }
}

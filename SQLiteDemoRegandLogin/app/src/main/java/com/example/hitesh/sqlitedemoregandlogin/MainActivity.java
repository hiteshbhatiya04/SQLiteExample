package com.example.hitesh.sqlitedemoregandlogin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, mobile, email,password;
    RadioGroup Gender;
    RadioButton male,female;
    Button submit;
    String gen;
    CheckBox play, listen;
    DatabaseHelper helper = new DatabaseHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.et_name);
        email = (EditText) findViewById(R.id.et_email);
        mobile = (EditText) findViewById(R.id.et_number);
        password = (EditText) findViewById(R.id.et_password);
        male = (RadioButton) findViewById(R.id.rad_male);
        female = (RadioButton)findViewById(R.id.rad_female);
        submit = (Button) findViewById(R.id.btn_submit);
        Gender = (RadioGroup) findViewById(R.id.rg_gender);
        play = (CheckBox)findViewById(R.id.chk_play);
        listen = (CheckBox)findViewById(R.id.chkp_listening);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na = name.getText().toString();
                String mob = mobile.getText().toString();
                String em = email.getText().toString();
                String pass = password.getText().toString();

                StringBuffer hobby = new StringBuffer();
                if (male.isChecked())
                {
                    gen = male.getText().toString();
                }
                if (female.isChecked())
                {
                    gen = female.getText().toString();
                }
                if(play.isChecked()){
                    hobby.append(" " + play.getText().toString());
                }
                if(listen.isChecked()){
                    hobby.append(" " + listen.getText().toString());
                }
                String hb = String.valueOf(hobby);
                if (na.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    return;

                } else if (mob.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Mobile No", Toast.LENGTH_SHORT).show();
                    return;


                } else if (mob.length() < 10) {
                    Toast.makeText(MainActivity.this, "Please Enter Valid Mobile No", Toast.LENGTH_SHORT).show();
                    return;

                } else if (em.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;

                } else if (!Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
                    Toast.makeText(MainActivity.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (pass.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (Gender.getCheckedRadioButtonId() == -1)
                {
                    // no radio buttons are checked
                    Toast.makeText(MainActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*else if (play.isChecked() == false && listen.isChecked() == false){
                    Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                else
                {
                    helper.AddContact(new ModelC(na,mob,em,pass,gen,hb));
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    Toast.makeText(MainActivity.this, ""+gen, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
                if (checked)
                    male.setText("Male");
            else
                male.setText("Female");

        }
    }
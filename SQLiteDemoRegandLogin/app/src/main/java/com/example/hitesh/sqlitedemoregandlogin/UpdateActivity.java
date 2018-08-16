package com.example.hitesh.sqlitedemoregandlogin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name, mobile, email,password;
    RadioGroup Gender;
    RadioButton rad_male,rad_female;
    Button update;
    CheckBox play, listen;
    int id;
    String gend;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper helper = new DatabaseHelper(UpdateActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        sqLiteDatabase = helper.getReadableDatabase();
        sqLiteDatabase = helper.getWritableDatabase();

        name = (EditText) findViewById(R.id.et_uname);
        mobile = (EditText) findViewById(R.id.et_unumber);
        email = (EditText) findViewById(R.id.et_uemail);
        password = (EditText) findViewById(R.id.et_upassword);
        rad_male = (RadioButton) findViewById(R.id.rad_umale);
        rad_female = (RadioButton) findViewById(R.id.rad_ufemale);
        update = (Button) findViewById(R.id.btn_updte);
        Gender = (RadioGroup) findViewById(R.id.rg_ugender);
        play = (CheckBox)findViewById(R.id.chk_uplay);
        listen = (CheckBox)findViewById(R.id.chkp_ulistening);

        Bundle data = getIntent().getExtras();
        String nam = data.getString("na");
        String num = data.getString("mo");
        String mail = data.getString("em");
        String pas = data.getString("pa");
        String gender = data.getString("gend");
        String hobby = data.getString("hob");
        String [] separated = hobby.split(" ");
        String lt = null;

        name.setText(nam);
        mobile.setText(num);
        email.setText(mail);
        password.setText(pas);
        if (gender.equals("Male")){
            Gender.check(R.id.rad_umale);
        }
        else Gender.check(R.id.rad_ufemale);
        if (separated[0] == "Playing")
        {
            play.isChecked();
        }
        if (separated[0] == "Listening")
        {
            listen.isChecked();
        }
        if (separated[0] == "Playing" && separated[1] == "Listening")
        {
            play.isChecked();
            listen.isChecked();
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelC c =new ModelC();

                String uname = name.getText().toString();
                String umob = mobile.getText().toString();
                String uemail = email.getText().toString();
                String upass = password.getText().toString();
                StringBuffer hobby = new StringBuffer();
                if (rad_male.isChecked())
                {
                    gend = rad_male.getText().toString();
                }
                if (rad_female.isChecked())
                {
                    gend = rad_female.getText().toString();
                }
                if(play.isChecked()){
                    hobby.append(" " + play.getText().toString());
                }
                if(listen.isChecked()){
                    hobby.append(" " + listen.getText().toString());
                }
                String hb = String.valueOf(hobby);
                //String uhoby = u_phone.getText().toString();

                c.setId(id);
                c.setName(uname);
                c.setPhone(umob);
                c.setEmail(uemail);
                c.setPassword(upass);
                c.setGender(gend);
                c.setHobby(hb);
                helper.Update(c);
                Intent intent1 = new Intent(UpdateActivity.this,DisplayActivity.class);
                intent1.putExtra("ema",uemail);
                Toast.makeText(UpdateActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent1);

            }
        });
        //Toast.makeText(this, ""+nam+num+mail+pas+gender+hobby, Toast.LENGTH_SHORT).show();


    }

    public void onRadioButtonClicked(View view) {
    }
}

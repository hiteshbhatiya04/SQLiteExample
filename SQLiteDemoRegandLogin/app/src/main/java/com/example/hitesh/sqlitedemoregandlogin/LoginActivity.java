package com.example.hitesh.sqlitedemoregandlogin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    EditText email,password;
    Button login;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        email = findViewById(R.id.et_lemail);
        password = findViewById(R.id.et_lpassword);
        login = findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelC modelC = new ModelC();
                String mail = email.getText().toString();
                String pass= password.getText().toString();
                cursor = sqLiteDatabase.rawQuery("SELECT *FROM " + databaseHelper.TB_name + " WHERE " + databaseHelper.user_email + "=? AND " + databaseHelper.user_password + "=?", new String[]{mail,pass} );
                if (cursor!=null){
                    if (cursor.getCount() > 0){
                        //cursor.moveToFirst();
                        Toast.makeText(LoginActivity.this, " Successfull ", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(LoginActivity.this,DisplayActivity.class);
                        intent.putExtra("ema",mail);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}

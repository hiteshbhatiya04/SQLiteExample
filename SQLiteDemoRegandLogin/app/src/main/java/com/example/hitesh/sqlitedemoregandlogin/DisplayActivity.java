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

import java.util.HashMap;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Button update, delete;
    ModelC modelC;

    TextView n, m, e, p, g, h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        n = (TextView) findViewById(R.id.c_name);
        m = (TextView) findViewById(R.id.c_number);
        e = (TextView) findViewById(R.id.c_email);
        p = (TextView) findViewById(R.id.c_password);
        g = (TextView) findViewById(R.id.c_gender);
        h = (TextView) findViewById(R.id.c_hobby);
        update = (Button) findViewById(R.id.btn_update);
        delete = (Button) findViewById(R.id.btn_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aaa = e.getText().toString();
                sqLiteDatabase.delete(databaseHelper.TB_name,databaseHelper.user_email+" =?",new String[]{aaa});
                sqLiteDatabase.close();

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = n.getText().toString();
                String umob = m.getText().toString();
                String uemail = e.getText().toString();
                String upass = p.getText().toString();
                String ugen = g.getText().toString();
                String uhobby = h.getText().toString();

                Intent intent = new Intent(DisplayActivity.this,UpdateActivity.class);
                intent.putExtra("na",uname);
                intent.putExtra("mo",umob);
                intent.putExtra("em",uemail);
                intent.putExtra("pa",upass);
                intent.putExtra("gend",ugen);
                intent.putExtra("hob",uhobby);
                startActivity(intent);

            }
        });
        String extras =getIntent().getExtras().getString("ema");
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT *FROM " + databaseHelper.TB_name + " WHERE " + databaseHelper.user_email + "=?", new String[]{extras} );

        if (cursor != null) {

            if (cursor.moveToFirst()) {

                /*Cursor rs = databaseHelper.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();*/

                String nam = cursor.getString(cursor.getColumnIndex(databaseHelper.user_name));
                String phon = cursor.getString(cursor.getColumnIndex(databaseHelper.user_phone));
                String emai = cursor.getString(cursor.getColumnIndex(databaseHelper.user_email));
                String pass = cursor.getString(cursor.getColumnIndex(databaseHelper.user_password));
                String gender = cursor.getString(cursor.getColumnIndex(databaseHelper.user_gender));
                String hobby = cursor.getString(cursor.getColumnIndex(databaseHelper.user_hobby));

                if (!cursor.isClosed()) {
                    cursor.close();
                }
                n.setText(nam);
                m.setText(phon);
                e.setText(emai);
                p.setText(pass);
                g.setText(gender);
                h.setText(hobby);
            }
        }
    }
}
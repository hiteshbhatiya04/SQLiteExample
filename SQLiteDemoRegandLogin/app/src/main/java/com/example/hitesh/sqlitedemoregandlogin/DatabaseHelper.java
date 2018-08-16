package com.example.hitesh.sqlitedemoregandlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

     public static int DB_version = 1;
     public static String DB_name = "DB_CONTACT";
     String TB_name = "Contact";
     String user_id = "id";
     String user_name = "name";
     String user_phone = "phone";
     String user_email = "email";
     String user_password = "password";
     String user_gender = "gender";
     String user_hobby = "hobby";

    public DatabaseHelper(Context context) {
        super(context,DB_name,null,DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query="create table "+TB_name+"("+user_id+" integer primary key, "+user_name+" text, "+user_phone+" text, "+user_email+" text unique, "+user_password+" text, "+user_gender+" text, "+user_hobby+" text)";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists "+TB_name);
        onCreate(sqLiteDatabase);

    }

    public void AddContact(ModelC modelC){
        SQLiteDatabase liteDatabase =this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(user_name,modelC.getName());
        values.put(user_phone,modelC.getPhone());
        values.put(user_email,modelC.getEmail());
        values.put(user_password,modelC.getPassword());
        values.put(user_gender,modelC.getGender());
        values.put(user_hobby,modelC.getHobby());
        liteDatabase.insert(TB_name,null,values);
        liteDatabase.close();

    }


    /*List<ModelC> ReadAllContact( String s){

        java.util.List<ModelC>contactList =new ArrayList<>();
        SQLiteDatabase database =this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM " + TB_name + " WHERE " + user_email + "="+s,null);
        if (cursor.moveToNext())
        {
            do {
                ModelC contact =new ModelC();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setPassword(cursor.getString(4));
                contact.setGender(cursor.getString(5));
                contact.setHobby(cursor.getString(6));
                contactList.add(contact);

            }while (cursor.moveToNext());
        }return (List<ModelC>) contactList;

    }*/

    public void delete(ModelC modelC) {
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete(TB_name,user_email+" =?",new String[]{String.valueOf(modelC.getEmail())});
        database.close();
    }

    public int Update(ModelC modelC) {

        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(user_name,modelC.getName());
        values.put(user_phone,modelC.getPhone());
        values.put(user_email,modelC.getEmail());
        values.put(user_password,modelC.getPassword());
        values.put(user_gender,modelC.getGender());
        values.put(user_hobby,modelC.getHobby());

        int i = database.update(TB_name,values,user_email+" =?",new String[]{String.valueOf(modelC.getEmail())});
        return i;

    }
}

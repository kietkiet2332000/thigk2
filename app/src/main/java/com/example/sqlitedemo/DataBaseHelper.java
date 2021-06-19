package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context){
        super(context, "DataBaseHelper11", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Authors(" + "id_Author integer primary key," +
                "name text, " +
                "address text," +
                "email text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Authors");
        onCreate(db);
    }
    public int insertAuthor(Author author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_Author", author.getId_Author());
        contentValues.put("name", author.getName());
        contentValues.put("address", author.getAddress());
        contentValues.put("email", author.getEmail());
        int result = (int) db.insert("Authors", null, contentValues);
        db.close();
        return result;
    }
    public ArrayList<Author> getAllAuthor(){
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Authors", null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()){
            list.add(new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public ArrayList<Author> getIDAuthor(int id_Author){
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Authors where id_Author="+id_Author, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Author author = new Author(cursor.getInt(0), cursor.getString(1),cursor.getString(2), cursor.getString(3));
        list.add(author);
        cursor.close();
        db.close();
        return list;
    }
}

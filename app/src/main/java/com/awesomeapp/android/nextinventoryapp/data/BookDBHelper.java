package com.awesomeapp.android.nextinventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.awesomeapp.android.nextinventoryapp.data.BookContract.BookEntery;


public class BookDBHelper extends SQLiteOpenHelper{

    //name of DB file
    private static final String DATABASE_NAME = "booksDataBase.db";
    private static final int DATABASE_VERSION = 1;


    public BookDBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_BOOKS_TABLE =  "CREATE TABLE " + BookEntery.TABLE_NAME + " ("
                + BookEntery._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookEntery.COLUMN_TITLE + " TEXT NOT NULL, "
                + BookEntery.COLUMN_PRICE + " INTEGER NOT NULL DEFAULT 0, "
                + BookEntery.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + BookEntery.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + BookEntery.COLUMN_SUPPLIER_PHONE + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

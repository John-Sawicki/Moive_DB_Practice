package com.example.android.moivedbpractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AddValuesToDB {

    public AddValuesToDB(SQLiteDatabase db, Context context){
        FavoriteDbHelper dbHelper = new FavoriteDbHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public void getMovieValues(String[] movieValues){

    }
}

package com.example.jahnavi.todolist;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by jahnavi on 03-11-2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    static final String ROW_ID="id";
    static final String NAME = "name";
    static final String POSITION = "position";
    static final String DB_NAME="b_DB";
    static final String TB_NAME="b_TB";
    static final int DB_VERSION='1';

    static final String CREATE_TB="CREATE TABLE b_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL,position TEXT NOT NULL);";

    Context ctx;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        ctx=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(CREATE_TB);
            Message.message(ctx,"On create called ");

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS"+TB_NAME);
        onCreate(db);
    }
}

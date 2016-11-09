package com.example.jahnavi.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jahnavi on 03-11-2016.
 */
public class DBAdapter {
    Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context ctx)
    {
        this.c=ctx;
        helper=new DBHelper(c);
    }
    public DBAdapter openDB()
    {
        try
        {
            db=helper.getWritableDatabase();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return this;
    }

    public void close()
    {
        try
        {
            helper.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public long add(String name,String pos)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(DBHelper.NAME,name);
            cv.put(DBHelper.POSITION, pos);

            return db.insert(DBHelper.TB_NAME,DBHelper.ROW_ID,cv);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

      return 0;
    }
    public Cursor getAlltasks()
    {
        String[] columns={DBHelper.ROW_ID,DBHelper.NAME,DBHelper.POSITION};
        return db.query(DBHelper.TB_NAME,columns,null,null,null,null,null);
    }

}

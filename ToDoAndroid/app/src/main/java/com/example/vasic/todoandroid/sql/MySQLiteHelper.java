package com.example.vasic.todoandroid.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_TASK = "task";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IS_DONE = "isdone";

    private static final String DATABASE_NAME = "task1.db";
    private static final int DATABASE_VERSION = 322;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " + TABLE_TASK + "( " + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_DESCRIPTION
            + " text not null, " + COLUMN_TITLE + " text not null," + COLUMN_IS_DONE+ " boolean not null default 0);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }

}
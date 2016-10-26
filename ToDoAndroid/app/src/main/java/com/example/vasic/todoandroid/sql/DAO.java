package com.example.vasic.todoandroid.sql;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.vasic.todoandroid.Classes.Task;

public class DAO {


    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_TITLE, MySQLiteHelper.COLUMN_DESCRIPTION, MySQLiteHelper.COLUMN_IS_DONE };

    public DAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    public Task createTask(String title, String description) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DESCRIPTION, description);
        values.put(MySQLiteHelper.COLUMN_TITLE, title);
        long insertId = database.insert(MySQLiteHelper.TABLE_TASK, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_TASK, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,null, null, null);
        cursor.moveToFirst();
        Task newTask = cursorToTask(cursor);
        cursor.close();
        return newTask;
    }

    public void deleteTask(Task task) {
        long id = task.getId();
        database.delete(MySQLiteHelper.TABLE_TASK, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public void updateTask(Task task) {
        long id = task.getId();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DESCRIPTION, task.getDescription());
        values.put(MySQLiteHelper.COLUMN_TITLE, task.getTitle());
        values.put(MySQLiteHelper.COLUMN_IS_DONE, task.getIsDone());

        database.update(MySQLiteHelper.TABLE_TASK,values, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public Task getTask(long id){
        Cursor cursor = database.query(MySQLiteHelper.TABLE_TASK, allColumns, "_id = " + id , null, null, null, null);
        Task task=new Task();
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            task = cursorToTask(cursor);
            cursor.close();
        }
        return task;
    }


    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
       Cursor cursor = database.query(MySQLiteHelper.TABLE_TASK, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Task task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return tasks;
    }

    private Task cursorToTask(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getLong(0));
        task.setTitle(cursor.getString(1));
        task.setDescription(cursor.getString(2));
        task.setDone(cursor.getString(3).equals("1"));
        return task;
    }

}
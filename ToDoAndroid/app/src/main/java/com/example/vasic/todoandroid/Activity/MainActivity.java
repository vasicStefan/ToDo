package com.example.vasic.todoandroid.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.vasic.todoandroid.sql.DAO;
import com.example.vasic.todoandroid.Adapter.MyArrayAdapter;
import com.example.vasic.todoandroid.R;
import com.example.vasic.todoandroid.Classes.Task;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {
    private DAO datasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ToDo");
        setSupportActionBar(toolbar);

        datasource=new DAO(this);
        datasource.open();


        ArrayList<Task> list=datasource.getAllTasks();

        MyArrayAdapter adapter = new MyArrayAdapter(this,list);
        ListView listView= (ListView) findViewById(R.id.listViewMain);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {

                Task value = (Task) adapter.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),TaskActivity.class);
                intent.putExtra("ID",value.getId());
                startActivity(intent);
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddTask.class);
                startActivity(intent);
            }
        });
    }
}

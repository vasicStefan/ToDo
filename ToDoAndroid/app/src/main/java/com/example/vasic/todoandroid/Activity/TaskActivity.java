package com.example.vasic.todoandroid.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vasic.todoandroid.sql.DAO;
import com.example.vasic.todoandroid.R;
import com.example.vasic.todoandroid.Classes.Task;

public class TaskActivity extends AppCompatActivity {
    private DAO datasource;
    Task task;
    EditText title,  description;

    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        checkBox=(CheckBox)findViewById(R.id.cBoxDone);
        title=(EditText) findViewById(R.id.titleUpdate);
        description=(EditText) findViewById(R.id.descriptionUpdate);

        Intent intent = getIntent();
        long taskID = intent.getLongExtra("ID",0);
        datasource=new DAO(this);
        datasource.open();
        task = datasource.getTask(taskID);


        title.setText(task.getTitle());
        description.setText(task.getDescription());
        checkBox.setChecked(task.getIsDone());


        Button button=(Button) findViewById(R.id.btnDelete);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                datasource.deleteTask(task);
                Intent i = new Intent(TaskActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


        Button update=(Button) findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!title.getText().toString().equals("")) {
                    task.setTitle(title.getText().toString());
                    task.setDescription(description.getText().toString());
                    task.setDone(checkBox.isChecked());

                    datasource.updateTask(task);

                    Intent intentB = new Intent(TaskActivity.this, MainActivity.class);
                    startActivity(intentB);

                }else{
                    Toast.makeText(TaskActivity.this, "Polje Title ne sme biti prazno", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }




}

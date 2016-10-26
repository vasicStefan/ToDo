package com.example.vasic.todoandroid.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.vasic.todoandroid.sql.DAO;
import com.example.vasic.todoandroid.R;

public class AddTask extends AppCompatActivity {
    private DAO datasource;
    EditText title;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        datasource=new DAO(this);
        datasource.open();

        title = (EditText) findViewById(R.id.TheTitle);
        description = (EditText)findViewById(R.id.editText);

        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(!title.getText().toString().equals("")) {
                    String Title = title.getText().toString();
                    String Description = description.getText().toString();
                    datasource.createTask(Title, Description);
                    Intent intent = new Intent(AddTask.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AddTask.this, "Polje Title ne sme biti prazno", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

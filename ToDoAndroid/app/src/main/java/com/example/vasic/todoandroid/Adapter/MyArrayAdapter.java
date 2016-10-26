package com.example.vasic.todoandroid.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.vasic.todoandroid.R;
import com.example.vasic.todoandroid.Classes.Task;
import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Task> {
    private final Context context;
    private final List<Task> tasks;

    public MyArrayAdapter(Context context, ArrayList<Task> values) {
        super(context, R.layout.rowlayout,values);
        this.context = context;
        this.tasks = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task current=tasks.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View View = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView title = (TextView) View.findViewById(R.id.firstLine);
        TextView description = (TextView) View.findViewById(R.id.secondLine);
        ImageView image = (ImageView) View.findViewById(R.id.icon);


        title.setText(tasks.get(position).getTitle());
        description.setText(tasks.get(position).getDescription());

        // change the icon

        if (!current.getIsDone()) {
            image.setImageResource(R.drawable.n_done);
            View.setBackgroundColor(Color.parseColor("#D8D8D8"));
        } else {
            image.setImageResource(R.drawable.done);
            View.setBackgroundColor(Color.parseColor("#6E6E6E"));
        }
        return View;
    }
}
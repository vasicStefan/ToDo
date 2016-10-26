package com.example.vasic.todoandroid.Classes;

/**
 * Created by vasic on 10/19/2016.
 */

public class Task {
    private long id;
    private String title;
    private String description;
    private boolean isDone=false;


    public Task(int id,  String title,  String description, Boolean isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone=isDone;
    }

    public Task() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return title + " " + description ;
    }

    public boolean getIsDone() {
        return isDone;
    }
}

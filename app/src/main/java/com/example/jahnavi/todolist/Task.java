package com.example.jahnavi.todolist;


/**
 * Created by jahnavi on 3/11/2016.
 */
public class Task {
    private String name,position;
    private int id;

    public Task(String name, String position, int id) {
        this.name = name;
        this.position = position;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

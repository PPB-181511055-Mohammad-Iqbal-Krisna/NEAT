package com.example.neat;

import java.io.Serializable;

public class Task implements Serializable {
    private String name;
    private String due;
    private boolean isDone;

    public Task (String name, String due){
        this.name = name;
        this.due = due;
        isDone = false;
    }

    public String getName() { return name; }

    public String getDue() { return due; }

    public boolean isDone() { return isDone; }
}

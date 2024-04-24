package com.example.new_lab_5;

import java.util.Date;

public class Task {
    private Date date;
    private String description;

    public Task(Date date, String description) {
        this.date = date;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}

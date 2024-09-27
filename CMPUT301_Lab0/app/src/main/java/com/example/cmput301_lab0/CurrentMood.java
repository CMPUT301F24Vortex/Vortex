package com.example.cmput301_lab0;

import java.util.Date;

public abstract class CurrentMood {
    private Date date;

    public CurrentMood () {

        this.date = new Date();
    }

    public CurrentMood (Date date) {

        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract String Mood();

}


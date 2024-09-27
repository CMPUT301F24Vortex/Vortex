package com.example.cmput301_lab0;

import java.util.Date;

public abstract class Tweet implements Tweetable {
    // attributes
    private String message;
    private Date date;


    // constructors
    public Tweet(String message) {
        this.message = message;
        this.date = new Date();

    }

    public Tweet(String message, Date date) {
        this.message = message;

        this.date = date;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public Date getDate() {

        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }

    public abstract Boolean isImportant();

}

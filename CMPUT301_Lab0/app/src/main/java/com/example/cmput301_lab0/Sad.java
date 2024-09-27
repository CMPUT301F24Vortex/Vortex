package com.example.cmput301_lab0;

import java.util.Date;

public class Sad extends CurrentMood{
    public Sad() {
        super();

    }
    public Sad(Date date) {
        super(date);

    }

    @Override
    public String Mood() {

        return "I'm sad";
    }
}

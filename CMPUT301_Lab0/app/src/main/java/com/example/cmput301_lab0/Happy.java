package com.example.cmput301_lab0;

import java.util.Date;

public class Happy extends CurrentMood{
    public Happy() {
        super();
    }


    public Happy(Date date) {
        super(date);

    }

    @Override
    public String Mood() {

        return "I'm happy";
    }
}

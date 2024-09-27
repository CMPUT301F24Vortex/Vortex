package com.example.cmput301_lab0;

import java.util.Date;

public class NormalTweet extends Tweet {
    public NormalTweet(String message) {
        super(message);

    }
    public NormalTweet(String message, Date date) {

        super(message, date);
    }

    @Override
    public Boolean isImportant() {
        return Boolean.FALSE; // Indicates that this tweet is not important
    }
}

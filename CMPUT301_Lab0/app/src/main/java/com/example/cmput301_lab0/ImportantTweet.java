package com.example.cmput301_lab0;

import java.util.Date;

public class ImportantTweet extends Tweet{
    public ImportantTweet(String message) {
        super(message);
    }
    public ImportantTweet(String message, Date date) {
        super(message, date);

    }

    @Override
    public Boolean isImportant() {
        return Boolean.TRUE;
    }

}


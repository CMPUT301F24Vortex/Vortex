package com.example.listycitylab3;

import java.io.Serializable;

public class Book implements Serializable {
    private String name;
    private String author;
    private String genre;
    private int publication_year;
    private boolean read_status;


    public Book(String name, String author, String genre, int publicationYear, boolean readStatus) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publication_year = publicationYear;
        this.read_status = readStatus;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public boolean getRead_status() {
        return read_status;
    }

    public void setRead_status(boolean read_status) {
        this.read_status = read_status;
    }
}
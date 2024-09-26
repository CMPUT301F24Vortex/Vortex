package com.example.vortex;

public abstract class Shape {
    // Integer fields to store coordinates
    private int x;
    private int y;

    // Constructor to initialize x and y
    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter and Setter for x
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    // Getter and Setter for y
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}

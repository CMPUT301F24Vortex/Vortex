package com.example.vortex;

public class Nonagon extends Shape {
    // Field for side length of the nonagon
    private int sideLength;

    // Constructor to initialize x, y, and side length
    public Nonagon(int x, int y, int sideLength) {
        this.x = x;
        this.y = y;
        this.sideLength = sideLength;
    }

    // Getter and Setter for sideLength
    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    // Method to calculate area of the nonagon
    public double getArea() {
        // Formula for area of a regular nonagon: (9 / 4) * sideLength^2 * cot(pi / 9)
        // cot(pi / 9) can be pre-calculated as approximately 2.747
        double cotPiOver9 = 2.747;
        return (9.0 / 4.0) * sideLength * sideLength * cotPiOver9;
    }

    // Method to calculate perimeter of the nonagon
    public int getPerimeter() {
        return 9 * sideLength;
    }
}

package com.example.vortex;

public class Star extends Shape{

    private int points;
    private double innerRadius;
    private double outerRadius;

    public Star (int x, int y, int points, double innerRadius, double outerRadius){
        super(x,y);
        this.points = points;
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
    }

    //getters and setters
    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public double getInnerRadius(){
        return innerRadius;
    }

    public void setInnerRadius(double innerRadius){
        this.innerRadius = innerRadius;
    }

    public double getOuterRadius(){
        return outerRadius;
    }
    public void setOuterRadius(double outerRadius){
        this.outerRadius = outerRadius;
    }
}

package com.example.myversion;

import javafx.scene.shape.Polygon;

public class polygonShape extends shape {

    private double[] points;

    public polygonShape(double[] points) {
        this.points = points;
    }

    @Override
    public Polygon createShape() {
        Polygon polygon = new Polygon(points);
        polygon.setFill(inColor);
        polygon.setStroke(borderColor);
        return polygon;
    }


}

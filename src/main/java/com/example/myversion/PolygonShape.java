package com.example.myversion;

import javafx.scene.shape.Polygon;

public class PolygonShape extends Shape {

    private double[] points;

    public PolygonShape(double[] points) {
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

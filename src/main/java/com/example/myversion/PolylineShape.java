package com.example.myversion;

import javafx.scene.shape.Polyline;

public class PolylineShape extends Shape {

    private double[] points;

    public PolylineShape(double[] points) {
        this.points = points;
    }
    @Override
    public Polyline createShape() {
        Polyline polyline = new Polyline(points);
        polyline.setFill(inColor);
        polyline.setStroke(borderColor);
        return polyline;
    }
}

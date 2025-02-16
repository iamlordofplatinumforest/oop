package com.example.myversion;

import javafx.scene.shape.Polyline;

public class polylineShape extends shape {

    private double[] points;

    public polylineShape(double[] points) {
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

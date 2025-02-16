package com.example.myversion;

import javafx.scene.shape.Ellipse;

public class ellipseShape extends shape {

    private double centerX, centerY, radiusX, radiusY;

    public ellipseShape(double X, double Y, double RX, double RY) {
        this.centerX = X;
        this.centerY = Y;
        this.radiusX = RX;
        this.radiusY = RY;
    }

    @Override
    public Ellipse createShape() {
        Ellipse ellipse = new Ellipse(centerX, centerY, radiusX, radiusY);
        ellipse.setFill(inColor);
        ellipse.setStroke(borderColor);
        return ellipse;
    }
}

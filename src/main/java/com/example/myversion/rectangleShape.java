package com.example.myversion;

import javafx.scene.shape.Rectangle;

public class rectangleShape extends shape {

    private double X, Y, height, width;

    public rectangleShape(double X, double Y, double height, double width) {
        this.X = X;
        this.Y = Y;
        this.height = height;
        this.width = width;
    }
    @Override
    public Rectangle createShape() {
        Rectangle rec = new Rectangle(X, Y, width, height);
        rec.setFill(inColor);
        rec.setStroke(borderColor);
        return rec;
    }


}

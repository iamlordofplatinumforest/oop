package com.example.myversion;

import javafx.scene.shape.Rectangle;

public class RectangleShape extends Shape {

    private double X, Y, height, width;

    public RectangleShape(double X, double Y, double height, double width) {
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

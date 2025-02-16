package com.example.myversion;

import javafx.scene.shape.Line;

public class lineShape extends shape {
    private double startX, startY, endX, endY;
    public lineShape(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public Line createShape() {
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(borderColor);
        return line;
    }

}

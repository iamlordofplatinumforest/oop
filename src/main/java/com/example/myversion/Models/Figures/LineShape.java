package com.example.myversion.Models.Figures;

import javafx.scene.shape.Line;
import javafx.scene.canvas.GraphicsContext;


public class LineShape implements Shape {
    private double startX, startY, endX, endY;

    @Override
    public void drawPreview(GraphicsContext gc, double startX, double startY, double endX, double endY, int angles) {
        gc.strokeLine(startX, startY, endX, endY);
    }

    @Override
    public void drawFinal(GraphicsContext gc, double startX, double startY, double endX, double endY, int angles) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        gc.strokeLine(startX, startY, endX, endY);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.strokeLine(startX, startY, endX, endY);
    }

    @Override
    public Shape clone() {
        LineShape clone = new LineShape();
        clone.startX = this.startX;
        clone.startY = this.startY;
        clone.endX = this.endX;
        clone.endY = this.endY;
        return clone;
    }
}

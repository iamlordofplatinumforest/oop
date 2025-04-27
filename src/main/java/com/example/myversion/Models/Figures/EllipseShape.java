package com.example.myversion.Models.Figures;

import javafx.scene.canvas.GraphicsContext;

public class EllipseShape implements Shape {
    private double startX, startY, endX, endY;

    @Override
    public void drawPreview(GraphicsContext gc, double startX, double startY, double endX, double endY, int angles) {
        gc.strokeOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY));
        gc.fillOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY));
    }

    @Override
    public void drawFinal(GraphicsContext gc, double startX, double startY, double endX, double endY, int angles) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        drawPreview(gc, startX, startY, endX, endY, angles);
    }

    @Override
    public void render(GraphicsContext gc) {
        drawPreview(gc, startX, startY, endX, endY, 0);
    }

    @Override
    public Shape clone() {
        EllipseShape clone = new EllipseShape();
        clone.startX = this.startX;
        clone.startY = this.startY;
        clone.endX = this.endX;
        clone.endY = this.endY;
        return clone;
    }
}
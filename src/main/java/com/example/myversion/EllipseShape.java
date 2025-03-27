package com.example.myversion;

import javafx.scene.canvas.GraphicsContext;

public class EllipseShape implements Shape {
    @Override
    public void drawPreview(GraphicsContext gc, double startX, double startY, double endX, double endY, int angles) {
        gc.strokeOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY));
        gc.fillOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY));
    }

    @Override
    public void drawFinal(GraphicsContext gc, double startX, double startY, double endX, double endY, int angles) {
        gc.strokeOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY));
        gc.fillOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY));
    }
}
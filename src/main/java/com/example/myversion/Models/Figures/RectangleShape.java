package com.example.myversion.Models.Figures;

import javafx.scene.canvas.GraphicsContext;

public class RectangleShape implements Shape {
    private double startX, startY, endX, endY;

    private void draw(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        double width = Math.abs(endX - startX);
        double height = Math.abs(endY - startY);
        gc.fillRect(Math.min(startX, endX), Math.min(startY, endY), width, height);
        gc.strokeRect(Math.min(startX, endX), Math.min(startY, endY), width, height);
    }

    @Override
    public void drawPreview(GraphicsContext gc, double startX, double startY, double endX, double endY, int angles) {
        draw(gc, startX, startY, endX, endY);
    }

    @Override
    public void drawFinal(GraphicsContext gc, double startX, double startY, double endX, double endY, int angles) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        draw(gc, startX, startY, endX, endY);
    }

    @Override
    public void render(GraphicsContext gc) {
        draw(gc, startX, startY, endX, endY);
    }

    @Override
    public Shape clone() {
        RectangleShape clone = new RectangleShape();
        clone.startX = this.startX;
        clone.startY = this.startY;
        clone.endX = this.endX;
        clone.endY = this.endY;
        return clone;
    }
}
package com.example.myversion.Models.Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PolylineShape implements Shape {
    private double startX, startY, endX, endY;
    private int segments;
    private Color strokeColor;
    private double lineWidth;

    @Override
    public void drawPreview(GraphicsContext gc, double startX, double startY, double endX, double endY, int segments) {
        saveDrawingStyles(gc);
        drawPolyline(gc, startX, startY, endX, endY, segments);
    }

    @Override
    public void drawFinal(GraphicsContext gc, double startX, double startY, double endX, double endY, int segments) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.segments = segments;
        saveDrawingStyles(gc);

        drawPolyline(gc, startX, startY, endX, endY, segments);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setStroke(strokeColor);
        gc.setLineWidth(lineWidth);
        drawPolyline(gc, startX, startY, endX, endY, segments);
    }

    private void drawPolyline(GraphicsContext gc, double startX, double startY, double endX, double endY, int segments) {
        double[] xPoints = new double[segments + 1];
        double[] yPoints = new double[segments + 1];

        double dx = (endX - startX) / segments;
        double dy = (endY - startY) / segments;
        boolean flip = true;

        for (int i = 0; i <= segments; i++) {
            xPoints[i] = startX + i * dx;
            if (i == 0 || i == segments) {
                yPoints[i] = startY + i * dy;
            } else {
                yPoints[i] = flip ? startY + i * dy + dy * 0.3
                        : startY + i * dy - dy * 0.3;
                flip = !flip;
            }
        }

        gc.strokePolyline(xPoints, yPoints, segments + 1);
    }

    private void saveDrawingStyles(GraphicsContext gc) {
        this.strokeColor = (Color) gc.getStroke();
        this.lineWidth = gc.getLineWidth();
    }

    @Override
    public Shape clone() {
        PolylineShape clone = new PolylineShape();
        clone.startX = this.startX;
        clone.startY = this.startY;
        clone.endX = this.endX;
        clone.endY = this.endY;
        clone.segments = this.segments;
        clone.strokeColor = this.strokeColor;
        clone.lineWidth = this.lineWidth;
        return clone;
    }
}
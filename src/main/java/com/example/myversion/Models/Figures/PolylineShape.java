package com.example.myversion.Models.Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

public class PolylineShape implements Shape {
    @Override
    public void drawPreview(GraphicsContext gc, double startX, double startY, double endX, double endY, int segments) {
        drawPolyline(gc, startX, startY, endX, endY, segments);
    }

    @Override
    public void drawFinal(GraphicsContext gc, double startX, double startY, double endX, double endY, int segments) {
        drawPolyline(gc, startX, startY, endX, endY, segments);
    }

    private void drawPolyline(GraphicsContext gc, double startX, double startY, double endX, double endY, int segments) {
        gc.setStroke(gc.getStroke());
        gc.setLineWidth(gc.getLineWidth());

        double dx = (endX - startX) / segments;
        double dy = (endY - startY) / segments;

        double x = startX;
        double y = startY;
        boolean flip = true;

        for (int i = 0; i < segments; i++) {
            double nextX = x + dx;
            double nextY = flip ? y + dy + (dy * 0.7) : y + dy - (dy * 0.7);
            flip = !flip;
            gc.strokeLine(x, y, nextX, nextY);
            x = nextX;
            y = nextY;
        }
    }
}

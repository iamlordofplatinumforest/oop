package com.example.myversion.Models.Figures;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

public class PolygonShape implements Shape {
    private double centerX, centerY, radius;
    private int sides;

    @Override
    public void drawPreview(GraphicsContext gc, double startX, double startY, double endX, double endY, int sides) {
        this.centerX = startX;
        this.centerY = startY;
        this.radius = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
        this.sides = sides;

        drawPolygon(gc);
    }

    @Override
    public void drawFinal(GraphicsContext gc, double startX, double startY, double endX, double endY, int sides) {
        drawPreview(gc, startX, startY, endX, endY, sides);
    }

    @Override
    public void render(GraphicsContext gc) {
        drawPolygon(gc);
    }

    private void drawPolygon(GraphicsContext gc) {
        List<List<Double>> vertices = calculatePolygonVertices(centerX, centerY, radius, sides);
        List<Double> xPoints = vertices.get(0);
        List<Double> yPoints = vertices.get(1);

        gc.fillPolygon(xPoints.stream().mapToDouble(Double::doubleValue).toArray(),
                yPoints.stream().mapToDouble(Double::doubleValue).toArray(), sides);
        gc.strokePolygon(xPoints.stream().mapToDouble(Double::doubleValue).toArray(),
                yPoints.stream().mapToDouble(Double::doubleValue).toArray(), sides);
    }

    private List<List<Double>> calculatePolygonVertices(double cx, double cy, double r, int sides) {
        List<Double> xPoints = new ArrayList<>();
        List<Double> yPoints = new ArrayList<>();

        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides;
            xPoints.add(cx + r * Math.cos(angle));
            yPoints.add(cy + r * Math.sin(angle));
        }

        return List.of(xPoints, yPoints);
    }

    public Shape clone() {
        PolygonShape clone = new PolygonShape();
        clone.centerX = this.centerX;
        clone.centerY = this.centerY;
        clone.radius = this.radius;
        clone.sides = this.sides;
        return clone;
    }
}


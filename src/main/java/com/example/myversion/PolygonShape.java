package com.example.myversion;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

public class PolygonShape implements Shape {

    @Override
    public void drawPreview(GraphicsContext gc, double startX, double startY, double endX, double endY, int sides) {
        double radius = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
        List<Double> xPoints = new ArrayList<>();
        List<Double> yPoints = new ArrayList<>();

        List<List<Double>> vertices = calculatePolygonVertices(startX, startY, radius, sides);
        xPoints = vertices.get(0);
        yPoints = vertices.get(1);

        // Рисуем предварительный эскиз
        gc.strokePolygon(
                xPoints.stream().mapToDouble(Double::doubleValue).toArray(),
                yPoints.stream().mapToDouble(Double::doubleValue).toArray(),
                sides
        );
    }

    @Override
    public void drawFinal(GraphicsContext gc, double startX, double startY, double endX, double endY, int sides) {
        double radius = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2)); // Рассчитываем радиус
        List<Double> xPoints = new ArrayList<>();
        List<Double> yPoints = new ArrayList<>();

        List<List<Double>> vertices = calculatePolygonVertices(startX, startY, radius, sides);
        xPoints = vertices.get(0);
        yPoints = vertices.get(1);

        // Заполняем многоугольник
        gc.fillPolygon(
                xPoints.stream().mapToDouble(Double::doubleValue).toArray(),
                yPoints.stream().mapToDouble(Double::doubleValue).toArray(),
                sides
        );
        // Рисуем контур многоугольника
        gc.strokePolygon(
                xPoints.stream().mapToDouble(Double::doubleValue).toArray(),
                yPoints.stream().mapToDouble(Double::doubleValue).toArray(),
                sides
        );
    }

    private List<List<Double>> calculatePolygonVertices(double centerX, double centerY, double radius, int sides) {
        List<Double> xPoints = new ArrayList<>();
        List<Double> yPoints = new ArrayList<>();

        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            xPoints.add(x);
            yPoints.add(y);
        }

        return List.of(xPoints, yPoints);
    }
}

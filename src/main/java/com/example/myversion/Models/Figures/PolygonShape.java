package com.example.myversion.Models.Figures;

import com.example.myversion.Models.Utils.ShapeStyle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

@JsonTypeName("polygon")
public class PolygonShape implements Shape {
    private double centerX;
    private double centerY;
    private double radius;
    private int sides;

    @JsonProperty("centerX")
    public double getCenterX() { return centerX; }

    @JsonProperty("centerX")
    public void setCenterX(double centerX) { this.centerX = centerX; }

    @JsonProperty("centerY")
    public double getCenterY() { return centerY; }

    @JsonProperty("centerY")
    public void setCenterY(double centerY) { this.centerY = centerY; }

    @JsonProperty("radius")
    public double getRadius() { return radius; }

    @JsonProperty("radius")
    public void setRadius(double radius) { this.radius = radius; }

    @JsonProperty("sides")
    public int getSides() { return sides; }

    @JsonProperty("sides")
    public void setSides(int sides) { this.sides = sides; }

    public PolygonShape() {}

    private ShapeStyle style;

    @Override
    public void setStyle(ShapeStyle style) {
        this.style = style;
    }

    @Override
    public ShapeStyle getStyle() {
        return style;
    }

    @Override
    public String getType() {
        return "polygon";
    }
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
        gc.setStroke(style.getStrokeColor());
        gc.setFill(style.getFillColor());
        gc.setLineWidth(style.getLineWidth());
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
        clone.style = this.style;
        return clone;
    }
}


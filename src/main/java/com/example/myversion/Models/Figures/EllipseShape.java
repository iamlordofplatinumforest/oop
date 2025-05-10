package com.example.myversion.Models.Figures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javafx.scene.canvas.GraphicsContext;

@JsonTypeName("ellipse")
public class EllipseShape implements Shape {
    private double startX;
    private double startY;
    private double endX;
    private double endY;

    @JsonProperty("startX")
    public double getStartX() { return startX; }

    @JsonProperty("startX")
    public void setStartX(double startX) { this.startX = startX; }

    @JsonProperty("startY")
    public double getStartY() { return startY; }

    @JsonProperty("startY")
    public void setStartY(double startY) { this.startY = startY; }

    @JsonProperty("endX")
    public double getEndX() { return endX; }

    @JsonProperty("endX")
    public void setEndX(double endX) { this.endX = endX; }

    @JsonProperty("endY")
    public double getEndY() { return endY; }

    @JsonProperty("endY")
    public void setEndY(double endY) { this.endY = endY; }

    public EllipseShape() { }

    @Override
    public String getType() {
        return "ellipse";
    }

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
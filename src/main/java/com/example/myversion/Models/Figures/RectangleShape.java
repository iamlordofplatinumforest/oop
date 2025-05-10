package com.example.myversion.Models.Figures;

import com.example.myversion.Models.Utils.ShapeStyle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javafx.scene.canvas.GraphicsContext;

@JsonTypeName("rectangle")
public class RectangleShape implements Shape {
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

    public RectangleShape() {}

    private ShapeStyle style;

    @Override
    public void setStyle(ShapeStyle style) {
        this.style = style;
    }

    @Override
    public ShapeStyle getStyle() {
        return style;
    }

    private void draw(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        double width = Math.abs(endX - startX);
        double height = Math.abs(endY - startY);
        gc.fillRect(Math.min(startX, endX), Math.min(startY, endY), width, height);
        gc.strokeRect(Math.min(startX, endX), Math.min(startY, endY), width, height);
    }
    @Override
    public String getType() {
        return "rectangle";
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
        gc.setStroke(style.getStrokeColor());
        gc.setFill(style.getFillColor());
        gc.setLineWidth(style.getLineWidth());
        draw(gc, startX, startY, endX, endY);
    }

    @Override
    public Shape clone() {
        RectangleShape clone = new RectangleShape();
        clone.startX = this.startX;
        clone.startY = this.startY;
        clone.endX = this.endX;
        clone.endY = this.endY;
        clone.style = this.style;
        return clone;
    }
}
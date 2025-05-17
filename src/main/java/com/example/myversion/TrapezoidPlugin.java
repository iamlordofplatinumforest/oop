package com.example.myversion;

import com.example.myversion.Models.Figures.Shape;
import com.example.myversion.Models.Figures.ShapePlugin;
import com.example.myversion.Models.Utils.ShapeStyle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TrapezoidPlugin implements ShapePlugin {
    private ShapeStyle style;

    @Override
    public void drawPreview(GraphicsContext gc, double startX, double startY, double endX, double endY, int ignored) {
        applyStyle(gc);
        draw(gc, startX, startY, endX, endY);
    }

    @Override
    public void drawFinal(GraphicsContext gc, double startX, double startY, double endX, double endY, int ignored) {
        applyStyle(gc);
        draw(gc, startX, startY, endX, endY);
    }

    private void draw(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        double topWidth = Math.abs(x2 - x1) * 0.6;
        double baseWidth = Math.abs(x2 - x1);
        double height = Math.abs(y2 - y1);
        double centerX = (x1 + x2) / 2;
        double topLeftX = centerX - topWidth / 2;
        double bottomLeftX = centerX - baseWidth / 2;
        double topY = Math.min(y1, y2);
        double bottomY = topY + height;

        double[] xPoints = {
                topLeftX,
                topLeftX + topWidth,
                bottomLeftX + baseWidth,
                bottomLeftX
        };

        double[] yPoints = {
                topY,
                topY,
                bottomY,
                bottomY
        };

        gc.fillPolygon(xPoints, yPoints, 4);
        gc.strokePolygon(xPoints, yPoints, 4);
    }

    @Override
    public String getDisplayName() {
        return "Трапеция";
    }

    @Override
    public Shape clone() {
        TrapezoidPlugin clone = new TrapezoidPlugin();
        if (this.style != null) clone.setStyle(this.style);
        return clone;
    }

    @Override
    public void render(GraphicsContext gc) {
        drawFinal(gc, 100, 100, 200, 200, 0); // Пример отображения
    }

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
        return "trapezoid";
    }

    private void applyStyle(GraphicsContext gc) {
        if (style == null) return;
        gc.setStroke(style.getStrokeColor() != null ? style.getStrokeColor() : Color.BLACK);
        gc.setFill(style.getFillColor() != null ? style.getFillColor() : Color.TRANSPARENT);
        gc.setLineWidth(style.getLineWidth());
    }
}


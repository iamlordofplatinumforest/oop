package com.example.myversion.Controllers;

import com.example.myversion.Models.Figures.Shape;
import com.example.myversion.Models.Utils.DrawingProcess;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ShapeController {
    private Shape currentShape;
    private double startX, startY;

    public void handleShapeSelection(String shapeType) {
        currentShape = DrawingProcess.getShape(shapeType);
    }

    public void checkShape(String value, ComboBox<String> anglesBox, Label anglesLbl) {
        if ("Многоугольник".equals(value) || "Ломаная".equals(value)) {
            anglesBox.getItems().setAll("3", "4", "5", "6", "7", "8");
            anglesBox.setValue("3");
            anglesBox.setVisible(true);
            anglesLbl.setVisible(true);
        } else {
            anglesBox.setVisible(false);
            anglesLbl.setVisible(false);
        }
    }

    public void handleMousePressed(double x, double y) {
        startX = x;
        startY = y;
    }

    public void handleMouseDragged(GraphicsContext gc, double endX, double endY,
                                   Color strokeColor, Color fillColor,
                                   double lineWidth, String anglesValue) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        if (currentShape != null) {
            gc.setStroke(strokeColor);
            gc.setLineWidth(lineWidth);
            gc.setFill(fillColor);
            currentShape.drawPreview(gc, startX, startY, endX, endY, Integer.parseInt(anglesValue));
        }
    }

    public void handleMouseReleased(GraphicsContext gc, double endX, double endY,
                                    Color strokeColor, Color fillColor,
                                    double lineWidth, String anglesValue) {
        if (currentShape != null) {
            gc.setStroke(strokeColor);
            gc.setLineWidth(lineWidth);
            gc.setFill(fillColor);
            currentShape.drawFinal(gc, startX, startY, endX, endY, Integer.parseInt(anglesValue));
        }
    }
}
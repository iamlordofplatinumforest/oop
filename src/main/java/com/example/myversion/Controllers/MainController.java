package com.example.myversion.Controllers;

import com.example.myversion.Models.Figures.Shape;
import com.example.myversion.Models.Utils.DrawingProcess;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MainController {
    private Canvas canvas;
    private GraphicsContext gc;
    private Shape currentShape;

    private ComboBox<String> shapeBox;
    private ComboBox<String> anglesBox;
    private Label anglesLabel;

    private Color strokeColor = Color.BLACK;
    private Color fillColor = Color.TRANSPARENT;
    private double lineWidth = 2.0;

    private double startX, startY;

    public void initialize(Canvas canvas, ComboBox<String> shapeBox,
                           ComboBox<String> anglesBox, Label anglesLabel) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.shapeBox = shapeBox;
        this.anglesBox = anglesBox;
        this.anglesLabel = anglesLabel;
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        shapeBox.setOnAction(e -> handleShapeSelection(shapeBox.getValue()));
        canvas.setOnMousePressed(e -> {
            startX = e.getX();
            startY = e.getY();
        });

        canvas.setOnMouseDragged(e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            if (currentShape != null) {
                gc.setStroke(strokeColor);
                gc.setLineWidth(lineWidth);
                gc.setFill(fillColor);
                currentShape.drawPreview(gc, startX, startY, e.getX(), e.getY(),
                        Integer.parseInt(anglesBox.getValue()));
            }
        });

        canvas.setOnMouseReleased(e -> {
            if (currentShape != null) {
                gc.setStroke(strokeColor);
                gc.setLineWidth(lineWidth);
                gc.setFill(fillColor);
                currentShape.drawFinal(gc, startX, startY, e.getX(), e.getY(),
                        Integer.parseInt(anglesBox.getValue()));
            }
        });
    }

    public void handleShapeSelection(String shapeType) {
        currentShape = DrawingProcess.getShape(shapeType);
        updateAnglesVisibility(shapeType);
    }

    private void updateAnglesVisibility(String shapeType) {
        boolean showAngles = "Многоугольник".equals(shapeType) || "Ломаная".equals(shapeType);
        anglesBox.setVisible(showAngles);
        anglesLabel.setVisible(showAngles);

        if (showAngles && anglesBox.getItems().isEmpty()) {
            anglesBox.getItems().addAll("3", "4", "5", "6", "7", "8");
            anglesBox.setValue("3");
        }
    }

    public void handleDrawAction() {
        if (currentShape != null) {
        }
    }
    
    public void setStrokeColor(Color color) {
        this.strokeColor = color;
    }

    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    public void setLineWidth(double width) {
        this.lineWidth = width;
    }
}

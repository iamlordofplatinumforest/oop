package com.example.myversion.Controllers;

import com.example.myversion.Models.Figures.Shape;
import com.example.myversion.Models.Utils.DrawingProcess;
import com.example.myversion.Views.GUI;
import javafx.scene.canvas.GraphicsContext;

public class ShapeController {
    private final GUI gui;
    private Shape currentShape;
    private double startX, startY;

    public ShapeController(GUI gui) {
        this.gui = gui;
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        gui.getShapeBox().setOnAction(e -> handleShapeSelection());
        gui.getCanvas().setOnMousePressed(e -> handleMousePressed(e.getX(), e.getY()));
        gui.getCanvas().setOnMouseDragged(e -> handleMouseDragged(e.getX(), e.getY()));
        gui.getCanvas().setOnMouseReleased(e -> handleMouseReleased(e.getX(), e.getY()));
        gui.getExtraButton().setOnAction(e -> gui.openExtraWindow());
    }

    private void handleShapeSelection() {
        String selectedShape = gui.getShapeBox().getValue();
        currentShape = DrawingProcess.getShape(selectedShape);
        updateAnglesVisibility(selectedShape);
    }

    private void updateAnglesVisibility(String shapeType) {
        boolean isPolygon = "Многоугольник".equals(shapeType) || "Ломаная".equals(shapeType);
        gui.getAnglesBox().setVisible(isPolygon);
        gui.getAnglesLbl().setVisible(isPolygon);

        if (isPolygon) {
            gui.getAnglesBox().getItems().setAll("3", "4", "5", "6", "7", "8");
            gui.getAnglesBox().setValue("3");
        }
    }

    private void handleMousePressed(double x, double y) {
        startX = x;
        startY = y;
    }

    private void handleMouseDragged(double endX, double endY) {
        GraphicsContext gc = gui.getCanvas().getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        if (currentShape != null) {
            applyDrawingStyles(gc);
            currentShape.drawPreview(gc, startX, startY, endX, endY, getAnglesValue());
        }
    }

    private void handleMouseReleased(double endX, double endY) {
        GraphicsContext gc = gui.getCanvas().getGraphicsContext2D();
        if (currentShape != null) {
            applyDrawingStyles(gc);
            currentShape.drawFinal(gc, startX, startY, endX, endY, getAnglesValue());
        }
    }

    private void applyDrawingStyles(GraphicsContext gc) {
        gc.setStroke(gui.getColorPicker().getValue());
        gc.setFill(gui.getFillColorPicker().getValue());
        gc.setLineWidth(gui.getThicknessSlider().getValue());
    }

    private int getAnglesValue() {
        try {
            return Integer.parseInt(gui.getAnglesBox().getValue());
        } catch (NumberFormatException e) {
            return 3;
        }
    }
}
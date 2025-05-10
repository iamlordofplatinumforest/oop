package com.example.myversion.Controllers;

import com.example.myversion.Models.Figures.Shape;
import com.example.myversion.Controllers.SerializationController;
import com.example.myversion.Models.Utils.*;
import com.example.myversion.Views.GUI;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.IOException;
import java.util.List;
import java.io.File;

public class ShapeController {
    private final GUI gui;
    private Shape currentShape;
    private double startX, startY;
    private final DrawingHistory history = new DrawingHistory();

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
        gui.getUndoButton().setOnAction(e -> undo());
        gui.getRedoButton().setOnAction(e -> redo());
        gui.getSaveButton().setOnAction(e -> handleSave());
        gui.getLoadButton().setOnAction(e -> handleLoad());
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
        redrawAll(gc);

        if (currentShape != null) {
            applyDrawingStyles(gc);
            currentShape.drawPreview(gc, startX, startY, endX, endY, getAnglesValue());
        }
    }

    private void handleMouseReleased(double endX, double endY) {
        GraphicsContext gc = gui.getCanvas().getGraphicsContext2D();

        if (currentShape != null) {
            applyDrawingStyles(gc);
            Shape finalShape = currentShape.clone();
            finalShape.drawFinal(gc, startX, startY, endX, endY, getAnglesValue());
            history.draw(finalShape);
            updateUndoRedoButtons();
        }
    }

    public void undo() {
        history.undo();
        redrawAll(gui.getCanvas().getGraphicsContext2D());
        updateUndoRedoButtons();
    }

    public void redo() {
        history.redo();
        redrawAll(gui.getCanvas().getGraphicsContext2D());
        updateUndoRedoButtons();
    }

    private void updateUndoRedoButtons() {
        gui.getUndoButton().setDisable(!history.isUndoAvailable());
        gui.getRedoButton().setDisable(!history.isRedoAvailable());
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

    private void redrawAll(GraphicsContext gc) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (Shape shape : history.getDrawnShapes()) {
            shape.render(gc);
        }
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    private void handleSave() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showSaveDialog(gui.getPrimaryStage());

        if (file != null) {
            SerializationController.saveShapesToFile(
                    file,
                    history.getDrawnShapes()
            );
        }
    }

    private void handleLoad() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showOpenDialog(gui.getPrimaryStage());

        if (file != null) {
            List<Shape> shapes = SerializationController.loadShapesFromFile(file);
            if (shapes != null) {
                history.loadShapes(shapes);
                redrawAll(gui.getCanvas().getGraphicsContext2D());
                updateUndoRedoButtons();
            }
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}

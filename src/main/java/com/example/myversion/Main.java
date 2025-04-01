package com.example.myversion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.myversion.Views.GUI;
import com.example.myversion.Controllers.*;


public class Main extends Application {
    private GUI gui;
    private ShapeController shapeController;

    @Override
    public void start(Stage primaryStage) {
        gui = new GUI();
        shapeController = new ShapeController();
        Scene scene = gui.createMainScene();
        setupEventHandlers();
        gui.setupPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupEventHandlers() {
        gui.getShapeBox().setOnAction(e -> {
            String selectedShape = gui.getShapeBox().getValue();
            shapeController.handleShapeSelection(selectedShape);
            shapeController.checkShape(selectedShape, gui.getAnglesBox(), gui.getAnglesLbl());
        });

        gui.getCanvas().setOnMousePressed(e ->
                shapeController.handleMousePressed(e.getX(), e.getY()));

        gui.getCanvas().setOnMouseDragged(e ->
                shapeController.handleMouseDragged(
                        gui.getCanvas().getGraphicsContext2D(),
                        e.getX(), e.getY(),
                        gui.getColorPicker().getValue(),
                        gui.getFillColorPicker().getValue(),
                        gui.getThicknessSlider().getValue(),
                        gui.getAnglesBox().getValue()
                ));

        gui.getCanvas().setOnMouseReleased(e ->
                shapeController.handleMouseReleased(
                        gui.getCanvas().getGraphicsContext2D(),
                        e.getX(), e.getY(),
                        gui.getColorPicker().getValue(),
                        gui.getFillColorPicker().getValue(),
                        gui.getThicknessSlider().getValue(),
                        gui.getAnglesBox().getValue()
                ));

        gui.getExtraButton().setOnAction(e -> gui.openExtraWindow((Stage) gui.getCanvas().getScene().getWindow()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
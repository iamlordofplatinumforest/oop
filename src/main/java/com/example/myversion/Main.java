package com.example.myversion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.scene.canvas.GraphicsContext;

public class Main extends Application {
    private Canvas canvas;
    private GraphicsContext gc;
    private Shape currentShape;
    private double startX, startY;
    private Slider thicknessSlider;
    private ColorPicker colorPicker;
    private ColorPicker fillColorPicker;
    private ComboBox<String> shapeBox;
    private ComboBox<String> anglesBox = new ComboBox<>();
    private Label anglesLbl = new Label("Углы:");
    private String[] shapes = {"Линия", "Прямоугольник", "Овал", "Многоугольник", "Ломаная"};

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        anglesLbl.setVisible(false);
        anglesBox.setVisible(false);
        anglesBox.setValue("3");

        shapeBox = new ComboBox<>();
        shapeBox.getItems().addAll(shapes);
        shapeBox.setValue(shapes[0]);
        shapeBox.setOnAction(e -> {
            String selectedShape = shapeBox.getValue();
            currentShape = DrawingProcess.getShape(selectedShape);
            checkShape();
        });

        thicknessSlider = new Slider(1, 10, 2);
        thicknessSlider.setShowTickLabels(true);
        thicknessSlider.setShowTickMarks(true);

        colorPicker = new ColorPicker(Color.BLACK);
        fillColorPicker = new ColorPicker(Color.TRANSPARENT);

        HBox controls = new HBox(20,
                new Label("Фигура:"), shapeBox,
                new Label("Толщина:"), thicknessSlider,
                new Label("Граница:"), colorPicker,
                new Label("Заливка:"), fillColorPicker,
                anglesLbl, anglesBox
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button drawButton = new Button("Рисовать");
        drawButton.setStyle("-fx-background-color: rgb(173, 216, 230);");

        Button undoButton = new Button("←");
        Button redoButton = new Button("→");

        Button extraButton = new Button("Дополнительно");
        extraButton.setOnAction(e -> openExtraWindow(primaryStage));

        controls.getChildren().addAll(spacer, drawButton, undoButton, redoButton, extraButton);
        VBox.setMargin(controls, new Insets(30, 20, 20, 20));
        controls.setPadding(new Insets(10));

        controls.setStyle("-fx-border-style: solid; -fx-border-width: 2; -fx-border-color: gray;");
        root.getChildren().addAll(controls, canvas);

        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty().subtract(controls.heightProperty()));

        canvas.setOnMousePressed(e -> {
            startX = e.getX();
            startY = e.getY();
        });

        canvas.setOnMouseDragged(e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            if (currentShape != null) {
                gc.setStroke(colorPicker.getValue());
                gc.setLineWidth(thicknessSlider.getValue());
                gc.setFill(fillColorPicker.getValue());
                currentShape.drawPreview(gc, startX, startY, e.getX(), e.getY(), Integer.parseInt(anglesBox.getValue()));
            }
        });

        canvas.setOnMouseReleased(e -> {
            if (currentShape != null) {
                gc.setStroke(colorPicker.getValue());
                gc.setLineWidth(thicknessSlider.getValue());
                gc.setFill(fillColorPicker.getValue());
                currentShape.drawFinal(gc, startX, startY, e.getX(), e.getY(), Integer.parseInt(anglesBox.getValue()));
            }
        });

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ООТПиСП");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
        shapeBox.setOnAction(e -> {
            String selectedShape = shapeBox.getValue();
            currentShape = DrawingProcess.getShape(selectedShape);
            checkShape();
        });
    }


    private void checkShape() {
        String value = shapeBox.getValue();
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


    private void openExtraWindow(Stage primaryStage) {
        Stage extraStage = new Stage();
        extraStage.setTitle("Дополнительные функции");

        Button saveFileButton = new Button("Сохранить");
        Button openFileButton = new Button("Загрузить");
        Button pluginsButton = new Button("Загрузить плагин");
        VBox layout = new VBox(10, saveFileButton, openFileButton, pluginsButton);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        extraStage.setScene(scene);

        extraStage.setResizable(false);
        extraStage.initOwner(primaryStage);
        extraStage.initModality(Modality.APPLICATION_MODAL);
        extraStage.setWidth(300);
        extraStage.setHeight(200);

        double parentWidth = primaryStage.getWidth();
        double parentHeight = primaryStage.getHeight();
        double parentX = primaryStage.getX();
        double parentY = primaryStage.getY();
        double x = parentX + (parentWidth - extraStage.getWidth()) / 2;
        double y = parentY + (parentHeight - extraStage.getHeight()) / 2;
        extraStage.setX(x);
        extraStage.setY(y);
        extraStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
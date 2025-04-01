package com.example.myversion.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUI {
    private Canvas canvas;
    private ComboBox<String> shapeBox;
    private ComboBox<String> anglesBox;
    private Label anglesLbl;
    private Slider thicknessSlider;
    private ColorPicker colorPicker;
    private ColorPicker fillColorPicker;
    private Button undoButton;
    private Button redoButton;
    private Button extraButton;

    public Scene createMainScene() {
        VBox root = new VBox(10);
        canvas = new Canvas(800, 600);
        createControls();

        HBox controls = new HBox(20,
                new Label("Фигура:"), shapeBox,
                new Label("Толщина:"), thicknessSlider,
                new Label("Граница:"), colorPicker,
                new Label("Заливка:"), fillColorPicker,
                anglesLbl, anglesBox
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        controls.getChildren().addAll(spacer, undoButton, redoButton, extraButton);

        VBox.setMargin(controls, new Insets(30, 20, 20, 20));
        controls.setPadding(new Insets(10));
        controls.setStyle("-fx-border-style: solid; -fx-border-width: 2; -fx-border-color: gray;");

        root.getChildren().addAll(controls, canvas);
        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty().subtract(controls.heightProperty()));

        return new Scene(root);
    }

    private void createControls() {
        shapeBox = new ComboBox<>();
        shapeBox.getItems().addAll("Линия", "Прямоугольник", "Овал", "Многоугольник", "Ломаная");
        shapeBox.setValue("Линия");
        anglesLbl = new Label("Углы:");
        anglesBox = new ComboBox<>();
        anglesBox.setVisible(false);
        anglesLbl.setVisible(false);
        anglesBox.setValue("3");

        thicknessSlider = new Slider(1, 10, 2);
        thicknessSlider.setShowTickLabels(true);
        thicknessSlider.setShowTickMarks(true);

        colorPicker = new ColorPicker(Color.BLACK);
        fillColorPicker = new ColorPicker(Color.TRANSPARENT);

        undoButton = new Button("←");
        redoButton = new Button("→");
        extraButton = new Button("Дополнительно");
    }

    public void setupPrimaryStage(Stage stage) {
        stage.setTitle("ООТПиСП");
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
    }

    public void openExtraWindow(Stage primaryStage) {
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

        extraStage.setX(primaryStage.getX() + (primaryStage.getWidth() - 300) / 2);
        extraStage.setY(primaryStage.getY() + (primaryStage.getHeight() - 200) / 2);
        extraStage.show();
    }

    public Canvas getCanvas() { return canvas; }
    public ComboBox<String> getShapeBox() { return shapeBox; }
    public ComboBox<String> getAnglesBox() { return anglesBox; }
    public Label getAnglesLbl() { return anglesLbl; }
    public Slider getThicknessSlider() { return thicknessSlider; }
    public ColorPicker getColorPicker() { return colorPicker; }
    public ColorPicker getFillColorPicker() { return fillColorPicker; }
    public Button getUndoButton() { return undoButton; }
    public Button getRedoButton() { return redoButton; }
    public Button getExtraButton() { return extraButton; }
}

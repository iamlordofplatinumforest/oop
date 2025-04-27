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
import lombok.Getter; //автоматически генерировать геттеры/сеттеры их во время компиляции с помощью аннотаций

@Getter
public class GUI {
    private final Stage primaryStage;
    private final Canvas canvas;
    private final ComboBox<String> shapeBox;
    private final ComboBox<String> anglesBox;
    private final Label anglesLbl;
    private final Slider thicknessSlider;
    private final ColorPicker colorPicker;
    private final ColorPicker fillColorPicker;
    private final Button extraButton;
    private final Button undoButton;
    private final Button redoButton;


    public GUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.canvas = new Canvas(800, 600);
        this.shapeBox = new ComboBox<>();
        this.anglesBox = new ComboBox<>();
        this.anglesLbl = new Label("Углы:");
        this.thicknessSlider = new Slider(1, 10, 2);
        this.colorPicker = new ColorPicker(Color.BLACK);
        this.fillColorPicker = new ColorPicker(Color.TRANSPARENT);
        this.undoButton = new Button("←");
        this.redoButton = new Button("→");
        this.extraButton = new Button("Дополнительно");
        setupControls();
    }

    private void setupControls() {
        shapeBox.getItems().addAll("Линия", "Прямоугольник", "Овал", "Многоугольник", "Ломаная");
        shapeBox.setValue("Линия");

        anglesBox.setVisible(false);
        anglesLbl.setVisible(false);
        anglesBox.setValue("3");

        thicknessSlider.setShowTickLabels(true);
        thicknessSlider.setShowTickMarks(true);
    }

    public Scene createMainScene() {
        VBox root = new VBox(10);
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

    public void setupPrimaryStage() {
        primaryStage.setTitle("ООТПиСП");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
    }

    public void openExtraWindow() {
        Stage extraStage = new Stage();
        extraStage.setTitle("Дополнительные функции");

        VBox layout = new VBox(10,
                new Button("Сохранить"),
                new Button("Загрузить"),
                new Button("Загрузить плагин")
        );
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        extraStage.setScene(new Scene(layout, 300, 200));
        extraStage.setResizable(false);
        extraStage.initOwner(primaryStage);
        extraStage.initModality(Modality.APPLICATION_MODAL);
        extraStage.setX(primaryStage.getX() + (primaryStage.getWidth() - 300) / 2);
        extraStage.setY(primaryStage.getY() + (primaryStage.getHeight() - 200) / 2);

        extraStage.showAndWait();
    }

}
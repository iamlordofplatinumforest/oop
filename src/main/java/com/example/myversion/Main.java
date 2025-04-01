package com.example.myversion;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.myversion.Views.GUI;
import com.example.myversion.Controllers.ShapeController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GUI gui = new GUI(primaryStage);
        new ShapeController(gui);

        primaryStage.setScene(gui.createMainScene());
        gui.setupPrimaryStage();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
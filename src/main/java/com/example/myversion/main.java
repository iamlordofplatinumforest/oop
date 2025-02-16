package com.example.myversion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        lineShape line = new lineShape(10, 10, 100, 100);
        line.setBorderColor(Color.BLACK);

        rectangleShape rectangle = new rectangleShape(150, 10, 150, 200);
        rectangle.setBorderColor(Color.BLACK);
        rectangle.setInColor(Color.YELLOW);

        ellipseShape ellipse = new ellipseShape(500, 110, 100, 60);
        ellipse.setInColor(Color.LIMEGREEN);
        ellipse.setBorderColor(Color.BLACK);

        double[] polygonPoints = {
                200.0, 250.0,
                260.0, 280.0,
                260.0, 350.0,
                200.0, 380.0,
                140.0, 350.0,
                140.0, 280.0
        };
        polygonShape polygon = new polygonShape(polygonPoints);
        polygon.setInColor(Color.AQUAMARINE);
        polygon.setBorderColor(Color.BLACK);

        double[] polylinePoints = {
                300.0, 250.0,
                400.0, 300.0,
                500.0, 250.0,
                600.0, 300.0
        };
        polylineShape polyline = new polylineShape(polylinePoints);
        polyline.setBorderColor(Color.RED);

        root.getChildren().addAll(line.createShape(), rectangle.createShape(), ellipse.createShape(), polygon.createShape(), polyline.createShape());

        Scene scene = new Scene(root, 610, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("OOТПиСП");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

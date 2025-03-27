package com.example.myversion;

import javafx.scene.shape.Line;
import javafx.scene.canvas.GraphicsContext;


public class LineShape implements Shape {
        @Override
        public void drawPreview(GraphicsContext gc, double startX, double startY, double endX, double endY, int angles) {
            gc.strokeLine(startX, startY, endX, endY);
        }

        @Override
        public void drawFinal(GraphicsContext gc, double startX, double startY, double endX, double endY, int angles) {
            gc.strokeLine(startX, startY, endX, endY);
        }

}

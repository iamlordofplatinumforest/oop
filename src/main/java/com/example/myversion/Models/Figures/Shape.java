package com.example.myversion.Models.Figures;

import javafx.scene.canvas.GraphicsContext;

public interface Shape {
    void drawPreview(GraphicsContext gc, double startX, double startY, double endX, double endY, int angels);
    void drawFinal(GraphicsContext gc, double startX, double startY, double endX, double endY, int angels);
    void render(GraphicsContext gc);
    Shape clone();
}
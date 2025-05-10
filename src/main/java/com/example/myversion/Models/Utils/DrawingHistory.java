package com.example.myversion.Models.Utils;

import com.example.myversion.Models.Figures.Shape;
import java.util.ArrayList;
import java.util.List;

public class DrawingHistory {
    private final List<Shape> history = new ArrayList<>();
    private int currentIndex = -1;

    public void draw(Shape shape) {
        history.subList(currentIndex + 1, history.size()).clear();
        history.add(shape.clone());
        currentIndex++;
    }

    public void undo() {
        if (isUndoAvailable()) {
            currentIndex--;
        }
    }

    public void redo() {
        if (isRedoAvailable()) {
            currentIndex++;
        }
    }

    public List<Shape> getDrawnShapes() {
        return new ArrayList<>(history.subList(0, currentIndex + 1));
    }

    public boolean isUndoAvailable() {
        return currentIndex >= 0;
    }

    public boolean isRedoAvailable() {
        return currentIndex < history.size() - 1;
    }

    public void loadShapes(List<Shape> shapes) {
        history.clear();
        history.addAll(shapes);
        currentIndex = history.size() - 1;
    }
}
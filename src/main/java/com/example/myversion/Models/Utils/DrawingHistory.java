package com.example.myversion.Models.Utils;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import com.example.myversion.Models.Figures.Shape;

public class DrawingHistory {
    private final Stack<Shape> onCanvasStack = new Stack<>();
    private final Stack<Shape> unDoStack = new Stack<>();

    public void draw(Shape shape) {
        unDoStack.clear();
        onCanvasStack.push(shape.clone());
    }

    public void undo() {
        if (!onCanvasStack.isEmpty()) {
            Shape shape = onCanvasStack.pop();
            unDoStack.push(shape);
        }
    }

    public void redo() {
        if (!unDoStack.isEmpty()) {
            Shape shape = unDoStack.pop();
            onCanvasStack.push(shape);
        }
    }

    public List<Shape> getDrawnShapes() {
        return new ArrayList<>(onCanvasStack);
    }

    public boolean isUndoAvailable() {
        return !onCanvasStack.isEmpty();
    }

    public boolean isRedoAvailable() {
        return !unDoStack.isEmpty();
    }
}
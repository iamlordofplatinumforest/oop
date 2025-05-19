package com.example.myversion.Models.Utils;

import java.util.*;

import com.example.myversion.Models.Figures.*;


public class DrawingProcess {
    private static final Map<String, Shape> shapes = new HashMap<>();
    private static final Map<String, Shape> pluginShapes = new HashMap<>();

    static {
        shapes.put("Линия", new LineShape());
        shapes.put("Прямоугольник", new RectangleShape());
        shapes.put("Овал", new EllipseShape());
        shapes.put("Ломаная", new PolylineShape());
        shapes.put("Многоугольник", new PolygonShape());
    }

    public static void addPluginShape(String name, Shape shape) {
        pluginShapes.put(name, shape);
    }

    public static Shape getShape(String type) {
        return Optional.ofNullable(pluginShapes.get(type))
                .orElse(shapes.get(type));
    }

    public static List<String> getBaseShapeNames() {
        return new ArrayList<>(shapes.keySet());
    }
}

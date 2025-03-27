package com.example.myversion;

import java.util.HashMap;
import java.util.Map;


public class DrawingProcess {
    private static final Map<String, Shape> shapes = new HashMap<>();

    static {
        shapes.put("Линия", new LineShape());
        shapes.put("Прямоугольник", new RectangleShape());
        shapes.put("Овал", new EllipseShape());
        shapes.put("Ломаная", new PolylineShape());
        shapes.put("Многоугольник", new PolygonShape());
    }

    public static Shape getShape(String type) {
        return shapes.get(type);
    }
}

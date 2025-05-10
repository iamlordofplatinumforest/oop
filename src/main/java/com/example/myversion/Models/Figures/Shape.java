package com.example.myversion.Models.Figures;

import javafx.scene.canvas.GraphicsContext;
import com.fasterxml.jackson.annotation.*;
import com.example.myversion.Models.Utils.ShapeStyle;

@JsonTypeInfo (
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = EllipseShape.class, name = "ellipse"),
        @JsonSubTypes.Type(value = LineShape.class, name = "line"),
        @JsonSubTypes.Type(value = PolygonShape.class, name = "polygon"),
        @JsonSubTypes.Type(value = PolylineShape.class, name = "polyline"),
        @JsonSubTypes.Type(value = RectangleShape.class, name = "rectangle"),
})
public interface Shape {
    void drawPreview(GraphicsContext gc, double startX, double startY, double endX, double endY, int angels);
    void drawFinal(GraphicsContext gc, double startX, double startY, double endX, double endY, int angels);
    void render(GraphicsContext gc);
    Shape clone();
    String getType();
    void setStyle(ShapeStyle style);
    ShapeStyle getStyle();
}
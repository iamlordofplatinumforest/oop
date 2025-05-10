package com.example.myversion.Models.Utils;

import javafx.scene.paint.Color;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ShapeStyle {
        private final Color strokeColor;
        private final Color fillColor;
        private final double lineWidth;

        @JsonCreator
        public ShapeStyle(
                @JsonProperty("strokeColor") Color strokeColor,
                @JsonProperty("fillColor") Color fillColor,
                @JsonProperty("lineWidth") double lineWidth) {
            this.strokeColor = strokeColor;
            this.fillColor = fillColor;
            this.lineWidth = lineWidth;
        }

        public Color getStrokeColor() { return strokeColor; }
        public Color getFillColor() { return fillColor; }
        public double getLineWidth() { return lineWidth; }
}

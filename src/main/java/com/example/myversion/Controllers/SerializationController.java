package com.example.myversion.Controllers;

import com.example.myversion.Models.Figures.Shape;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import com.example.myversion.Models.Utils.ColorDeserializer;
import com.example.myversion.Models.Utils.ColorSerializer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SerializationController {
    private static final ObjectMapper objectMapper = createObjectMapper();
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(
                mapper.getVisibilityChecker()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
        );
        SimpleModule colorModule = new SimpleModule();
        colorModule.addSerializer(Color.class, new ColorSerializer());
        colorModule.addDeserializer(Color.class, new ColorDeserializer());
        mapper.registerModule(colorModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        return mapper;
    }

    public static void saveShapesToFile(File file, List<Shape> shapes) {
        try {
            objectMapper.writeValue(file, shapes);
        } catch (IOException e) {
            showError("Ошибка сохранения: " + e.getMessage());
        }
    }

    public static List<Shape> loadShapesFromFile(File file) {
        try {
            JavaType type = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Shape.class);
            return objectMapper.readValue(file, type);
        } catch (IOException e) {
            showError("Ошибка загрузки: " + e.getMessage());
            return null;
        }
    }

    private static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
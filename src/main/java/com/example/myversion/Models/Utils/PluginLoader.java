package com.example.myversion.Models.Utils;

import com.example.myversion.Models.Figures.ShapePlugin;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.stream.StreamSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;

public class PluginLoader {
    public static List<ShapePlugin> loadPlugin(File jarFile, ObjectMapper mapper) {
        List<ShapePlugin> plugins = new ArrayList<>();
        try {
            URLClassLoader classLoader = new URLClassLoader(
                    new URL[]{jarFile.toURI().toURL()},
                    PluginLoader.class.getClassLoader()
            );

            ServiceLoader<ShapePlugin> serviceLoader =
                    ServiceLoader.load(ShapePlugin.class, classLoader);

            for (ShapePlugin plugin : serviceLoader) {
                plugins.add(plugin);
                mapper.registerSubtypes(new NamedType(plugin.getClass(), plugin.getType()));
            }

            return plugins;

        } catch (Exception e) {
            System.err.println("Ошибка загрузки плагина: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
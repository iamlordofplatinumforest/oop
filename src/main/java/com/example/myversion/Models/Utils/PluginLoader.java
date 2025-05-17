
package com.example.myversion.Models.Utils;

import com.example.myversion.Models.Figures.ShapePlugin;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.stream.StreamSupport;

public class PluginLoader {
    public static List<ShapePlugin> loadPlugin(File jarFile) {
        List<ShapePlugin> plugins = new ArrayList<>();
        try {
            URLClassLoader classLoader = new URLClassLoader(
                    new URL[]{jarFile.toURI().toURL()},
                    PluginLoader.class.getClassLoader()
            );

            ServiceLoader<ShapePlugin> serviceLoader =
                    ServiceLoader.load(ShapePlugin.class, classLoader);

            StreamSupport.stream(serviceLoader.spliterator(), false)
                    .forEach(plugins::add);

            return plugins;

        } catch (Exception e) {
            System.err.println("Ошибка загрузки плагина: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}

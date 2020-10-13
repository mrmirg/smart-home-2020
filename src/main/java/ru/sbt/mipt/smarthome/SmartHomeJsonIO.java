package ru.sbt.mipt.smarthome;


import com.google.gson.*;

import ru.sbt.mipt.smarthome.components.HomeComponent;
import ru.sbt.mipt.smarthome.components.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SmartHomeJsonIO implements SmartHomeIO {
    private final Gson json;


    public SmartHomeJsonIO() {
        this.json = new GsonBuilder().setPrettyPrinting().create();
    }


    public void writeHome(SmartHome smartHome, String path) throws IOException {
        if (smartHome == null) {
            throw new IllegalArgumentException("smartHome must be non-null");
        }
        String jsonString = json.toJson(smartHome);
        Path pathpath = Paths.get(path);
        BufferedWriter writer = Files.newBufferedWriter(pathpath);
        writer.write(jsonString);
        writer.flush();
    }


    public SmartHome readHome(String path) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(path)));
        return json.fromJson(jsonString, SmartHome.class);
    }
}

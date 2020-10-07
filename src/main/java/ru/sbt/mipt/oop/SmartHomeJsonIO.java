package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SmartHomeJsonIO implements SmartHomeIO {
    private final Gson jsonBuilder;

    public SmartHomeJsonIO() {
        this.jsonBuilder = new GsonBuilder().setPrettyPrinting().create();
    }

    public void writeHome(SmartHome smartHome, String path) throws IOException {
        if (smartHome == null) {
            throw new IllegalArgumentException("smartHome must be non-null");
        }
        String jsonString = jsonBuilder.toJson(smartHome);
        Path pathpath = Paths.get(path);
        System.out.println(jsonString);
        BufferedWriter writer = Files.newBufferedWriter(pathpath);
        writer.write(jsonString);
        writer.flush();
    }

    public SmartHome readHome(String path) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(path)));
        return jsonBuilder.fromJson(jsonString, SmartHome.class);
    }
}

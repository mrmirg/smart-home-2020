package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class HomeBuilder {

    public static SmartHome buildSampleHome() {
        return new SmartHome(Arrays.asList(
                new Room("kitchen", Arrays.asList(
                        new Light("1", false),
                        new Light("2", true),
                        new Door("3", false)
                )),
                new Room("bathroom", Arrays.asList(
                        new Light("4", true),
                        new Door("5", false)
                )),
                new Room("bedroom", Arrays.asList(
                        new Light("6", false),
                        new Light("7", false),
                        new Light("8", false),
                        new Door("9", false)
                )),
                new Room("hall", Arrays.asList(
                        new Light("10", false),
                        new Light("11", false),
                        new Light("12", false),
                        new Door("13", false)
                ))
        ));
    }
}

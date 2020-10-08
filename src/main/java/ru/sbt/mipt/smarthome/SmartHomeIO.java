package ru.sbt.mipt.smarthome;

import ru.sbt.mipt.smarthome.components.SmartHome;

import java.io.IOException;

public interface SmartHomeIO {
    void writeHome(SmartHome home, String path) throws IOException;
    SmartHome readHome(String path) throws IOException;
}

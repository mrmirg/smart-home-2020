package ru.sbt.mipt.oop;

import java.io.File;
import java.io.IOException;

public interface SmartHomeIO {
    void writeHome(SmartHome home, String path) throws IOException;
    SmartHome readHome(String path) throws IOException;
}

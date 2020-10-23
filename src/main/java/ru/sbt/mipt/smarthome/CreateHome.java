package ru.sbt.mipt.smarthome;


import ru.sbt.mipt.smarthome.components.SmartHome;
import java.io.IOException;


public class CreateHome {
    public static void main(String[] args) throws IOException {
        SmartHome smartHome = HomeBuilder.buildSampleHome("alarm");
        SmartHomeIO homeIO = new SmartHomeJsonIO();
        homeIO.writeHome(smartHome, "src/main/resources/samplehome.json");
    }
}

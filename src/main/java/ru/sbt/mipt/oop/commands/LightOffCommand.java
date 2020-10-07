package ru.sbt.mipt.oop.commands;


import ru.sbt.mipt.oop.SmartHome;

public class LightOffCommand implements SensorCommand {

    public boolean sendCommand(String deviceId) {
        System.out.println("We're sending the command!");
        return true;
    }
}

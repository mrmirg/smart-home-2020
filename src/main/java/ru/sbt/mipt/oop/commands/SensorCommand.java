package ru.sbt.mipt.oop.commands;


import ru.sbt.mipt.oop.SmartHome;

public interface SensorCommand {
    boolean sendCommand(String deviceId);
}

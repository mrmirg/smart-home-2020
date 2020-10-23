package ru.sbt.mipt.smarthome.commands;


public interface SensorCommand {
    boolean sendCommand(String componentId);
}

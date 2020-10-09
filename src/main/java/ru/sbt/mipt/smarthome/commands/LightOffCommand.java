package ru.sbt.mipt.smarthome.commands;


public class LightOffCommand implements SensorCommand {

    @Override
    public boolean sendCommand(String componentId) {
        System.out.println("We're sending the command!");
        return true;
    }
}

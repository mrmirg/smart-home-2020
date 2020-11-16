package ru.sbt.mipt.smarthome.commands;


import ru.sbt.mipt.smarthome.actions.*;
import ru.sbt.mipt.smarthome.commands.SensorCommand;
import ru.sbt.mipt.smarthome.components.SmartHome;


public class TurnAllLights implements SensorCommand {
    private final SmartHome smartHome;
    private final boolean lightsOn;


    public TurnAllLights(SmartHome smartHome, boolean lightsOn) {
        this.smartHome = smartHome;
        this.lightsOn = lightsOn;
    }


    @Override
    public boolean execute() {
        return smartHome.applyAction(new ru.sbt.mipt.smarthome.actions.TurnAllLights(lightsOn));
    }
}

package ru.sbt.mipt.smarthome.commands;

import ru.sbt.mipt.smarthome.actions.TurnHallroomLights;
import ru.sbt.mipt.smarthome.components.SmartHome;

public class TurnHallLight implements SensorCommand {
    private final SmartHome smartHome;
    private final boolean lightsOn;
    private final String halldoorId;


    public TurnHallLight(SmartHome smartHome, boolean lightsOn) {
        this.smartHome = smartHome;
        this.lightsOn = lightsOn;
        this.halldoorId = "halldoor";
    }


    public TurnHallLight(SmartHome smartHome, boolean lightsOn, String hallId) {
        this.smartHome = smartHome;
        this.lightsOn = lightsOn;
        this.halldoorId = hallId;
    }


    @Override
    public boolean execute() {
        return smartHome.applyAction(new TurnHallroomLights(halldoorId, lightsOn));
    }
}

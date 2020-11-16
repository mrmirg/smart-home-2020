package ru.sbt.mipt.smarthome.commands;

import ru.sbt.mipt.smarthome.actions.OpenCloseDoor;
import ru.sbt.mipt.smarthome.components.SmartHome;

public class OpenHalldoor implements SensorCommand {
    private final SmartHome smartHome;
    private final boolean isOpen;
    private final String halldoorId;


    public OpenHalldoor(SmartHome smartHome, boolean isOpen, String halldoorId) {
        this.smartHome = smartHome;
        this.isOpen = isOpen;
        this.halldoorId = halldoorId;
    }


    @Override
    public boolean execute() {
        return smartHome.applyAction(new OpenCloseDoor(halldoorId, isOpen));
    }
}

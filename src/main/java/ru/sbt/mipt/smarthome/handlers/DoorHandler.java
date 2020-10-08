package ru.sbt.mipt.smarthome.handlers;

import ru.sbt.mipt.smarthome.actions.OpenCloseDoor;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.DoorClosed;
import ru.sbt.mipt.smarthome.events.DoorOpened;
import ru.sbt.mipt.smarthome.events.SensorEvent;

public class DoorHandler implements SensorEventHandler {
    private final SmartHome smartHome;

    public DoorHandler(SmartHome home) {
        this.smartHome = home;
    }

    public boolean processEvent(SensorEvent event) {
        if (event instanceof DoorOpened || event instanceof DoorClosed) {
            boolean opened = event instanceof DoorOpened;

            boolean success = smartHome.applyAction(new OpenCloseDoor(
                    event.getComponentId(),
                    opened
            ));
            System.out.println(
                    (opened ? "Opening" : "Closing") +
                            " door " +
                            event.getComponentId() + " | " +
                            (success ? "\tSuccess" : "\tFailure")
            );
            return success;
        }
        return false;
    }
}

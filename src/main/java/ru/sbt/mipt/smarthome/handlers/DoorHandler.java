package ru.sbt.mipt.smarthome.handlers;


import ru.sbt.mipt.smarthome.actions.LockDoor;
import ru.sbt.mipt.smarthome.actions.OpenCloseDoor;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.*;


public class DoorHandler implements SensorEventHandler {
    private final SmartHome smartHome;


    public DoorHandler(SmartHome home) {
        this.smartHome = home;
    }


    @Override
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

        // DRY..
        if (event instanceof DoorLocked || event instanceof DoorUnlocked) {
            boolean locked = event instanceof DoorLocked;
            boolean success = smartHome.applyAction(new LockDoor(
                    event.getComponentId(),
                    locked
            ));
            System.out.println(
                    (locked ? "Locking" : "Unlocking") +
                            " door " +
                            event.getComponentId() + " | " +
                            (success ? "\tSuccess" : "\tFailure")
            );
            return success;
        }
        return false;
    }
}

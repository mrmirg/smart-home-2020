package ru.sbt.mipt.smarthome.handlers;


import ru.sbt.mipt.smarthome.actions.LockDoor;
import ru.sbt.mipt.smarthome.actions.OpenCloseDoor;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.DoorLocked;
import ru.sbt.mipt.smarthome.events.DoorOpened;
import ru.sbt.mipt.smarthome.events.SensorEvent;


public class DoorHandler implements SensorEventHandler {
    private final SmartHome smartHome;


    public DoorHandler(SmartHome home) {
        this.smartHome = home;
    }


    @Override
    public boolean processEvent(SensorEvent event) {
        if (event instanceof DoorOpened) {
            var doorOpened = (DoorOpened) event;
            boolean success = smartHome.applyAction(new OpenCloseDoor(
                    event.getComponentId(),
                    doorOpened.isOpened()
            ));
            System.out.println(
                    (doorOpened.isOpened() ? "Opening" : "Closing") +
                            " door " +
                            event.getComponentId() + " | " +
                            (success ? "\tSuccess" : "\tFailure")
            );
            return success;
        }

        // DRY..
        if (event instanceof DoorLocked) {
            var doorLocked = (DoorLocked) event;
            boolean success = smartHome.applyAction(new LockDoor(
                    event.getComponentId(),
                    doorLocked.isLocked()
            ));
            System.out.println(
                    (doorLocked.isLocked() ? "Locking" : "Unlocking") +
                            " door " +
                            event.getComponentId() + " | " +
                            (success ? "\tSuccess" : "\tFailure")
            );
            return success;
        }
        return false;
    }
}

package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.events.DoorClosedEvent;
import ru.sbt.mipt.oop.events.DoorOpenedEvent;
import ru.sbt.mipt.oop.events.SensorEvent;

public class DoorHandler implements SensorEventHandler {

    public boolean processEvent(SensorEvent event) {
        if (event instanceof DoorOpenedEvent) {
            DoorOpenedEvent doorEvent = (DoorOpenedEvent) event;
            Door doorDevice = (Door) doorEvent.getDevice();
            doorDevice.setOpen(true);
            return true;
        }

        if (event instanceof DoorClosedEvent) {
            DoorClosedEvent doorEvent = (DoorClosedEvent) event;
            Door doorDevice = (Door) doorEvent.getDevice();
            doorDevice.setOpen(false);
            return true;
        }



        return false;
    }
}

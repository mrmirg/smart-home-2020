package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.devices.Device;
import ru.sbt.mipt.oop.devices.Door;

public class DoorClosedEvent implements SensorEvent {
    private final Door device;

    public DoorClosedEvent(Door device) {
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }
}

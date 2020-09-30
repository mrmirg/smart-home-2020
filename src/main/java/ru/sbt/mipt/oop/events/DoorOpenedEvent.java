package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.devices.Device;
import ru.sbt.mipt.oop.devices.Door;

public class DoorOpenedEvent implements SensorEvent {
    private final Door device;

    public DoorOpenedEvent(Door device) {
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }


}

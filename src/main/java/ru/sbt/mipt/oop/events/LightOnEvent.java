package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.devices.Device;
import ru.sbt.mipt.oop.devices.Light;

public class LightOnEvent implements SensorEvent {
    private final Light device;

    public LightOnEvent(Light device) {
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }
}

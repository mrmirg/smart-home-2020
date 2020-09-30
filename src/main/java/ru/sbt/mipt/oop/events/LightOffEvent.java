package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.devices.Device;
import ru.sbt.mipt.oop.devices.Light;

public class LightOffEvent implements SensorEvent {
    private final Light device;

    public LightOffEvent(Light device) {
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }
}

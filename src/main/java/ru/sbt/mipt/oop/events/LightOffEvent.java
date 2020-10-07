package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.devices.Device;
import ru.sbt.mipt.oop.devices.Light;

public class LightOffEvent implements SensorEvent {
    private final String deviceId;

    public LightOffEvent(String deviceId) {
        if (deviceId == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}

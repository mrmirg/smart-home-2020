package ru.sbt.mipt.smarthome.events;


public class DoorClosed implements SensorEvent {
    private final String deviceId;

    public DoorClosed(String deviceId) {
        if (deviceId == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        this.deviceId = deviceId;
    }

    public String getComponentId() {
        return deviceId;
    }
}

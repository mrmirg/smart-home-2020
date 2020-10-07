package ru.sbt.mipt.oop.events;


public class DoorClosedEvent implements SensorEvent {
    private final String deviceId;

    public DoorClosedEvent(String deviceId) {
        if (deviceId == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}

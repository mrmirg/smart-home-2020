package ru.sbt.mipt.oop.events;


public class DoorOpenedEvent implements SensorEvent {
    private final String deviceId;

    public DoorOpenedEvent(String deviceId) {
        if (deviceId == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }


}

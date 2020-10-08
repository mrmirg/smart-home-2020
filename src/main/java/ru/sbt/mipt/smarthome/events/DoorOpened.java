package ru.sbt.mipt.smarthome.events;


public class DoorOpened implements SensorEvent {
    private final String deviceId;

    public DoorOpened(String deviceId) {
        if (deviceId == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        this.deviceId = deviceId;
    }

    public String getComponentId() {
        return deviceId;
    }


}

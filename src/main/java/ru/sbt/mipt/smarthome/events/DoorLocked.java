package ru.sbt.mipt.smarthome.events;


public class DoorLocked implements SensorEvent {
    private final String deviceId;


    public DoorLocked(String deviceId) {
        if (deviceId == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        this.deviceId = deviceId;
    }


    @Override
    public String getComponentId() {
        return deviceId;
    }
}

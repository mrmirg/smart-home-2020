package ru.sbt.mipt.smarthome.events;


public class DoorLocked implements SensorEvent {
    private final String deviceId;
    private final boolean locked;


    public DoorLocked(String deviceId, boolean locked) {
        if (deviceId == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        this.deviceId = deviceId;
        this.locked = locked;
    }


    public boolean isLocked() {
        return locked;
    }


    @Override
    public String getComponentId() {
        return deviceId;
    }
}

package ru.sbt.mipt.smarthome.events;


public class DoorOpened implements SensorEvent {
    private final String deviceId;
    private final boolean opened;


    public DoorOpened(String deviceId, boolean opened) {
        if (deviceId == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        this.deviceId = deviceId;
        this.opened = opened;
    }


    public boolean isOpened() {
        return opened;
    }

    @Override
    public String getComponentId() {
        return deviceId;
    }
}

package ru.sbt.mipt.smarthome.events;


public class LightOff implements SensorEvent {
    private final String deviceId;


    public LightOff(String deviceId) {
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

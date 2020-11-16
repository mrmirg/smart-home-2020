package ru.sbt.mipt.smarthome.events;


public class LightOn implements SensorEvent {
    private final String deviceId;
    private final boolean isOn;


    public LightOn(String deviceId, boolean isOn) {
        if (deviceId == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        this.deviceId = deviceId;
        this.isOn = isOn;
    }


    public boolean isOn() {
        return isOn;
    }


    @Override
    public String getComponentId() {
        return deviceId;
    }
}

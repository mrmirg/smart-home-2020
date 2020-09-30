package ru.sbt.mipt.oop.devices;

public class Light implements Device {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String id() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}

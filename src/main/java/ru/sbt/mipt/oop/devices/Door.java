package ru.sbt.mipt.oop.devices;

public class Door implements Device {
    private final String id;
    private boolean isOpen;

    public Door(String id, boolean isOpen) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String id() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}

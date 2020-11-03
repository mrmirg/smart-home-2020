package ru.sbt.mipt.smarthome.components;


public class Door implements HomeComponent {
    private final String id;
    private boolean isOpen;
    private boolean isLocked = false;


    public Door(String id, boolean isOpen) {
        this.isOpen = isOpen;
        this.id = id;
    }


    @Override
    public String getId() {
        return id;
    }


    public void setOpen(boolean open) {
        isOpen = open;
    }


    public boolean isOpened() {
        return isOpen;
    }


    public boolean isLocked() {
        return isLocked;
    }


    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}

package ru.sbt.mipt.smarthome.components;


public class Door implements HomeComponent {
    private final String id;
    private boolean isOpen;


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
}

package ru.sbt.mipt.smarthome.events;


// decorates DoorOpened event
public class HalldoorOpened implements SensorEvent {
    private final DoorOpened doorOpenedEvent;


    public HalldoorOpened(DoorOpened doorOpenedEvent) {
        this.doorOpenedEvent = doorOpenedEvent;
    }


    public boolean isOpened() {
        return doorOpenedEvent.isOpened();
    }


    @Override
    public String getComponentId() {
        return doorOpenedEvent.getComponentId();
    }
}

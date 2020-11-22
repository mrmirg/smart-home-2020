package ru.sbt.mipt.smarthome.events;


import com.coolcompany.smarthome.events.CCSensorEvent;


public class CCSensorEventAdapter implements SensorEvent {
    private final CCSensorEvent event;


    public CCSensorEventAdapter(CCSensorEvent event) {
        this.event = event;
    }


    @Override
    public String getComponentId() {
        return event.getObjectId();
    }
}

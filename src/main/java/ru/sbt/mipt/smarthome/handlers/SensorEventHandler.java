package ru.sbt.mipt.smarthome.handlers;


import ru.sbt.mipt.smarthome.events.SensorEvent;


public interface SensorEventHandler {
    boolean processEvent(SensorEvent event);
}

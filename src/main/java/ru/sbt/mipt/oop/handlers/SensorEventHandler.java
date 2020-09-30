package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface SensorEventHandler {
    boolean processEvent(SensorEvent event);
}

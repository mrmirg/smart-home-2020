package ru.sbt.mipt.smarthome;

import ru.sbt.mipt.smarthome.events.SensorEvent;

public interface HandlerManager {
    boolean processEvent(SensorEvent event);
}

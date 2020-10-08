package ru.sbt.mipt.smarthome.events;

public interface EventGenerator {
    SensorEvent nextEvent();
}

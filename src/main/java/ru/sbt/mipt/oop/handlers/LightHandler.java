package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.events.LightOffEvent;
import ru.sbt.mipt.oop.events.LightOnEvent;
import ru.sbt.mipt.oop.events.SensorEvent;

public class LightHandler implements SensorEventHandler {

    public boolean processEvent(SensorEvent event) {
        if (event instanceof LightOnEvent) {
            LightOnEvent lightEvent = (LightOnEvent) event;
            Light lightDevice = (Light) lightEvent.getDevice();
            lightDevice.setOn(true);
            return true;
        }
        if (event instanceof LightOffEvent) {
            LightOffEvent lightEvent = (LightOffEvent) event;
            Light lightDevice = (Light) lightEvent.getDevice();
            lightDevice.setOn(false);
            return true;
        }
        return false;
    }
}

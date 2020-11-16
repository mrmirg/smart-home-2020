package ru.sbt.mipt.smarthome.handlers;


import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.smarthome.events.DoorLocked;
import ru.sbt.mipt.smarthome.events.DoorOpened;
import ru.sbt.mipt.smarthome.events.LightOn;
import ru.sbt.mipt.smarthome.events.SensorEvent;


public class EventFactory {
    public static SensorEvent makeSensorEvent(CCSensorEvent event) {
        String id = event.getObjectId();
        switch (event.getEventType()) {
            case "LightIsOn"    : return new LightOn(id, true);
            case "LightIsOff"   : return new LightOn(id, false);
            case "DoorIsOpen"   : return new DoorOpened(id, true);
            case "DoorIsClosed" : return new DoorOpened(id, false);
            case "DoorIsLocked" : return new DoorLocked(id, true);
            case "DoorIsUnlocked" : return new DoorLocked(id, false);

            default: throw new RuntimeException("Illegal event type");
        }
    }
}

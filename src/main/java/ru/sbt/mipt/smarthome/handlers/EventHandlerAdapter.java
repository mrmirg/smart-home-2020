package ru.sbt.mipt.smarthome.handlers;


import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.smarthome.events.DoorLocked;
import ru.sbt.mipt.smarthome.events.DoorOpened;
import ru.sbt.mipt.smarthome.events.LightOn;


public class EventHandlerAdapter implements EventHandler {
    private final SensorEventHandler handler;


    public EventHandlerAdapter(SensorEventHandler handler) {
        this.handler = handler;
    }


    @Override
    public void handleEvent(CCSensorEvent event) {

        String id = event.getObjectId();
        switch (event.getEventType()) {
            case "LightIsOn"    : handler.processEvent(new LightOn(id, true));    break;
            case "LightIsOff"   : handler.processEvent(new LightOn(id, false));   break;
            case "DoorIsOpen"   : handler.processEvent(new DoorOpened(id, true)); break;
            case "DoorIsClosed" : handler.processEvent(new DoorOpened(id, false)); break;
            case "DoorIsLocked" : handler.processEvent(new DoorLocked(id, true)); break;
            case "DoorIsUnlocked" : handler.processEvent(new DoorLocked(id, false)); break;

            default: throw new RuntimeException("Illegal event type");
        }
    }
}

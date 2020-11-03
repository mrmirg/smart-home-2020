package ru.sbt.mipt.smarthome.handlers;


import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.smarthome.events.*;


public class EventHandlerAdapter implements EventHandler {
    private final SensorEventHandler handler;


    public EventHandlerAdapter(SensorEventHandler handler) {
        this.handler = handler;
    }


    @Override
    public void handleEvent(CCSensorEvent event) {

        String id = event.getObjectId();
        switch (event.getEventType()) {
            case "LightIsOn"    : handler.processEvent(new LightOn(id));    break;
            case "LightIsOff"   : handler.processEvent(new LightOff(id));   break;
            case "DoorIsOpen"   : handler.processEvent(new DoorOpened(id)); break;
            case "DoorIsClosed" : handler.processEvent(new DoorClosed(id)); break;
            case "DoorIsLocked" : handler.processEvent(new DoorLocked(id)); break;
            case "DoorIsUnlocked" : handler.processEvent(new DoorUnlocked(id)); break;

            default: throw new RuntimeException("Illegal event type");
        }
    }
}

package ru.sbt.mipt.smarthome.handlers;


import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.smarthome.events.DoorClosed;
import ru.sbt.mipt.smarthome.events.DoorOpened;
import ru.sbt.mipt.smarthome.events.LightOff;
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
            case "LightIsOn"    : handler.processEvent(new LightOn(id));    break;
            case "LightIsOff"   : handler.processEvent(new LightOff(id));   break;
            case "DoorIsOpen"   : handler.processEvent(new DoorOpened(id)); break;
            case "DoorIsClosed" : handler.processEvent(new DoorClosed(id)); break;
            case "DoorIsLocked" : break;
            case "DoorIsUnlocked" : break;

            default: throw new RuntimeException("Illegal event type");
        }
    }
}

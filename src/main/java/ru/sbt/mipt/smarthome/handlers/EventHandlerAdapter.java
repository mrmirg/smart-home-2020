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
        handler.processEvent(
                EventFactory.makeSensorEvent(event)
        );
    }
}

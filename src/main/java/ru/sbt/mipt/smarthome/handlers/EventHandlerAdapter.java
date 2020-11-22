package ru.sbt.mipt.smarthome.handlers;


import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.smarthome.events.SensorEvent;

import java.util.function.Function;


public class EventHandlerAdapter implements EventHandler {
    private final SensorEventHandler handler;
    private final Function<CCSensorEvent, SensorEvent> eventMapping;


    public EventHandlerAdapter(
            SensorEventHandler handler,
            Function<CCSensorEvent, SensorEvent> eventMapping) {
        this.handler = handler;
        this.eventMapping = eventMapping;
    }


    @Override
    public void handleEvent(CCSensorEvent event) {
        handler.processEvent(
                eventMapping.apply(event)
        );
    }
}

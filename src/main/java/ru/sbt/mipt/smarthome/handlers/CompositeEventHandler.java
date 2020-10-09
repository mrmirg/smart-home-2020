package ru.sbt.mipt.smarthome.handlers;

import ru.sbt.mipt.smarthome.events.SensorEvent;

import java.util.ArrayList;
import java.util.Collection;

public class CompositeEventHandler implements SensorEventHandler {
    private final Collection<ru.sbt.mipt.smarthome.handlers.SensorEventHandler> handlers;

    public CompositeEventHandler(Collection<ru.sbt.mipt.smarthome.handlers.SensorEventHandler> handlers) {
        this.handlers = handlers;
    }

    public CompositeEventHandler() {
        this.handlers = new ArrayList<>();
    }

    @Override
    public boolean processEvent(SensorEvent event) {
        boolean handleResult = false;
        for (ru.sbt.mipt.smarthome.handlers.SensorEventHandler handler : handlers) {
            handleResult |= handler.processEvent(event);
        }
        return handleResult;
    }
}

package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.events.SensorEvent;

import java.util.ArrayList;
import java.util.Collection;

public class HandlerManager implements SensorEventHandler {
    private final Collection<SensorEventHandler> handlers;

    public HandlerManager(Collection<SensorEventHandler> handlers) {
        this.handlers = handlers;
    }

    public HandlerManager() {
        this.handlers = new ArrayList<>();
    }

    public boolean processEvent(SensorEvent event) {
        boolean handleResult = false;
        for (SensorEventHandler handler : handlers) {
            handleResult |= handler.processEvent(event);
        }
        return handleResult;
    }
}

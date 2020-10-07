package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.handlers.SensorEventHandler;

import java.util.ArrayList;
import java.util.Collection;

public class HandlerManagerImpl implements HandlerManager {
    private final Collection<SensorEventHandler> handlers;

    public HandlerManagerImpl(Collection<SensorEventHandler> handlers) {
        this.handlers = handlers;
    }

    public HandlerManagerImpl() {
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

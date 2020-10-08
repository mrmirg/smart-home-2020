package ru.sbt.mipt.smarthome;

import ru.sbt.mipt.smarthome.events.EventGenerator;
import ru.sbt.mipt.smarthome.events.SensorEvent;

public class EventLoop {
    private final EventGenerator eventGenerator;
    private final HandlerManager handlerManager;

    public EventLoop(EventGenerator eventGenerator, HandlerManager handlerManager) {
        this.eventGenerator = eventGenerator;
        this.handlerManager = handlerManager;
    }

    public void spin() {
        for (
                SensorEvent event = eventGenerator.nextEvent();
                event != null;
                event = eventGenerator.nextEvent()
        ) {
            handlerManager.processEvent(event);
        }
    }
}

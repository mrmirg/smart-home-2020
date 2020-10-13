package ru.sbt.mipt.smarthome;


import ru.sbt.mipt.smarthome.events.EventGenerator;
import ru.sbt.mipt.smarthome.events.SensorEvent;
import ru.sbt.mipt.smarthome.handlers.SensorEventHandler;


public class EventLoop {
    private final EventGenerator eventGenerator;
    private final SensorEventHandler sensorEventHandler;


    public EventLoop(EventGenerator eventGenerator, SensorEventHandler sensorEventHandler) {
        this.eventGenerator = eventGenerator;
        this.sensorEventHandler = sensorEventHandler;
    }


    public void spin() {
        for (
                SensorEvent event = eventGenerator.nextEvent();
                event != null;
                event = eventGenerator.nextEvent()
        ) {
            sensorEventHandler.processEvent(event);
        }
    }
}

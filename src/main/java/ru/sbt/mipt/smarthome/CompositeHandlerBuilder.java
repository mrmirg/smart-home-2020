package ru.sbt.mipt.smarthome;


import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.handlers.*;
import java.util.Arrays;


public class CompositeHandlerBuilder {
    private final SmartHome smartHome;


    public CompositeHandlerBuilder(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    public SensorEventHandler buildDefault() {
        return new CompositeEventHandler(Arrays.asList(
                new DoorHandler(smartHome),
                new LightHandler(smartHome)
        ));
    }


    public SensorEventHandler buildDefaultWithAlarm(String alarmId) {
        return new AlarmDecorator(
            smartHome,
            alarmId,
            new CompositeEventHandler(Arrays.asList(
                    new DoorHandler(smartHome),
                    new LightHandler(smartHome),
                    new AlarmHandler(smartHome)
            ))
        );
    }
}

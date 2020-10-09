package ru.sbt.mipt.smarthome;

import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.handlers.CompositeEventHandler;
import ru.sbt.mipt.smarthome.handlers.DoorHandler;
import ru.sbt.mipt.smarthome.handlers.LightHandler;
import ru.sbt.mipt.smarthome.handlers.SensorEventHandler;

import java.util.Arrays;

public class CompositeHandlerBuilder {
    private SmartHome smartHome;

    public CompositeHandlerBuilder(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public SensorEventHandler buildDefaultManager() {
        return new CompositeEventHandler(Arrays.asList(
                new DoorHandler(smartHome),
                new LightHandler(smartHome)
        ));
    }
}

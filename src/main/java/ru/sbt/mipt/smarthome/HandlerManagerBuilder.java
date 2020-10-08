package ru.sbt.mipt.smarthome;

import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.handlers.DoorHandler;
import ru.sbt.mipt.smarthome.handlers.LightHandler;

import java.util.Arrays;

public class HandlerManagerBuilder {
    private SmartHome smartHome;

    public HandlerManagerBuilder(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public HandlerManager buildDefaultManager() {
        return new HandlerManagerImpl(Arrays.asList(
                new DoorHandler(smartHome),
                new LightHandler(smartHome)
        ));
    }
}

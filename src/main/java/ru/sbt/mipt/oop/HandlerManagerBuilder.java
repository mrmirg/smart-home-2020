package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.DoorHandler;
import ru.sbt.mipt.oop.handlers.LightHandler;

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

package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.DoorHandler;
import ru.sbt.mipt.oop.handlers.HandlerManager;
import ru.sbt.mipt.oop.handlers.LightHandler;

import java.util.Arrays;

public class HandlerManagerBuilder {
    public static HandlerManager buildDefaultManager() {
        return new HandlerManager(Arrays.asList(
                new DoorHandler(),
                new LightHandler()
        ));
    }
}

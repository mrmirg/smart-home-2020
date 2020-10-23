package ru.sbt.mipt.smarthome.actions;

import ru.sbt.mipt.smarthome.components.HomeComponent;

public interface Action {
    boolean act(HomeComponent component);
}

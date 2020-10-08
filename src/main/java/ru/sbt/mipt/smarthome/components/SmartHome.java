package ru.sbt.mipt.smarthome.components;

import ru.sbt.mipt.smarthome.actions.Action;
import ru.sbt.mipt.smarthome.Actionable;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeComponent, Actionable {
    private final Collection<HomeComponent> components;
    private final String id;

    public SmartHome() {
        id = "root";
        components = new ArrayList<>();
    }

    public SmartHome(String id, Collection<HomeComponent> components) {
        this.components = components;
        this.id = id;
    }

    public void addRoom(HomeComponent component) {
        components.add(component);
    }

    public String getId() {
        return id;
    }

    public boolean applyAction(Action action) {
        boolean result = false;
        for (HomeComponent component : components) {
            result |= action.act(component);
        }
        return result;
    }
}

package ru.sbt.mipt.smarthome.components;


import ru.sbt.mipt.smarthome.actions.Action;
import ru.sbt.mipt.smarthome.Actionable;
import java.util.Collection;


public class Room implements HomeComponent, Actionable {
    private final Collection<HomeComponent> components;
    private final String name;
    private final String id;


    public Room(String name, String id, Collection<HomeComponent> homeComponents) {
        this.components = homeComponents;
        this.name = name;
        this.id = id;
    }


    @Override
    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    @Override
    public boolean applyAction(Action action) {
        boolean result = false;
        for (HomeComponent component : components) {
            if (component instanceof Actionable) {
                ((Actionable) component).applyAction(action);
            }
            result |= action.act(component);
        }
        return result;
    }
}

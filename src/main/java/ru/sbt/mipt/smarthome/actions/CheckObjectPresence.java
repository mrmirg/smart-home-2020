package ru.sbt.mipt.smarthome.actions;


import ru.sbt.mipt.smarthome.components.HomeComponent;


public class CheckObjectPresence implements Action {
    private final String componentId;


    public CheckObjectPresence(String componentId) {
        this.componentId = componentId;
    }


    @Override
    public boolean act(HomeComponent component) {
        return componentId.equals(component.getId());
    }
}

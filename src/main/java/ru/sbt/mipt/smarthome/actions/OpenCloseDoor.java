package ru.sbt.mipt.smarthome.actions;


import ru.sbt.mipt.smarthome.components.HomeComponent;
import ru.sbt.mipt.smarthome.components.Door;


public class
OpenCloseDoor implements Action {
    private final boolean opened;
    private final String componentId;


    public OpenCloseDoor(String componentId, boolean opened) {
        if (componentId == null) {
            throw new IllegalArgumentException("component id must be non-null");
        }
        this.opened = opened;
        this.componentId = componentId;
    }


    @Override
    public boolean act(HomeComponent component) {
        if (component instanceof Door && componentId.equals(component.getId())) {
            ((Door) component).setOpen(opened);
            return true;
        }
        return false;
    }
}

package ru.sbt.mipt.smarthome.actions;

import ru.sbt.mipt.smarthome.components.Door;
import ru.sbt.mipt.smarthome.components.HomeComponent;

public class LockDoor implements Action {
    private final boolean locked;
    private final String componentId;


    public LockDoor(String componentId, boolean locked) {
        this.locked = locked;
        this.componentId = componentId;
    }

    @Override
    public boolean act(HomeComponent component) {
        if (component instanceof Door && componentId.equals(component.getId())) {
            if (((Door) component).isOpened()) {
                return false;
            }
            ((Door) component).setLocked(locked);
            return true;
        }
        return false;
    }
}

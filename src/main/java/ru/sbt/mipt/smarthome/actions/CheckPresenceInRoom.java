package ru.sbt.mipt.smarthome.actions;

import ru.sbt.mipt.smarthome.components.HomeComponent;
import ru.sbt.mipt.smarthome.components.Room;


public class CheckPresenceInRoom implements Action {
    private final String componentId;

    public CheckPresenceInRoom(String componentId) {
        this.componentId = componentId;
    }

    @Override
    public boolean act(HomeComponent component) {
        if (component instanceof Room) {
            return ((Room) component).applyAction(this);
        }
        return componentId.equals(component.getId());
    }
}

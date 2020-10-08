package ru.sbt.mipt.smarthome.actions;

import ru.sbt.mipt.smarthome.Actionable;
import ru.sbt.mipt.smarthome.components.HomeComponent;
import ru.sbt.mipt.smarthome.components.Light;

public class TurnLight implements Action {
    private final boolean lightOn;
    private final String componentId;

    public TurnLight(String componentId, boolean lightOn) {
        if (componentId == null) {
            throw new IllegalArgumentException("component id must be non-null");
        }
        this.lightOn = lightOn;
        this.componentId = componentId;
    }

    public boolean act(HomeComponent component) {
        if (component instanceof Light && componentId.equals(component.getId())) {
            ((Light) component).setOn(lightOn);
            return true;
        }
        if (component instanceof Actionable) {
            return ((Actionable) component).applyAction(this);
        }
        return false;
    }
}

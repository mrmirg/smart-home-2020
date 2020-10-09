package ru.sbt.mipt.smarthome.actions;

import ru.sbt.mipt.smarthome.Actionable;
import ru.sbt.mipt.smarthome.components.HomeComponent;
import ru.sbt.mipt.smarthome.components.Light;

public class TurnAllLights implements Action {
    private final boolean lightOn;

    public TurnAllLights(boolean lightOn) {
        this.lightOn = lightOn;
    }

    @Override
    public boolean act(HomeComponent component) {
        if (component instanceof Light) {
            ((Light) component).setOn(lightOn);
            return true;
        }
        if (component instanceof Actionable) {
            return ((Actionable) component).applyAction(this);
        }
        return false;
    }
}
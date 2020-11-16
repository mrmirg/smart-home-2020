package ru.sbt.mipt.smarthome.actions;

import ru.sbt.mipt.smarthome.components.HomeComponent;
import ru.sbt.mipt.smarthome.components.Light;

public class CheckAllLightsOff implements Action {
    @Override
    public boolean act(HomeComponent component) {
        if (component instanceof Light) {
            return !((Light) component).isOn();
        }
        return false;
    }
}

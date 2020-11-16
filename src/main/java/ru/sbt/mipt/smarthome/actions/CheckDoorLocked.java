package ru.sbt.mipt.smarthome.actions;


import ru.sbt.mipt.smarthome.components.Door;
import ru.sbt.mipt.smarthome.components.HomeComponent;

import java.util.Objects;


public class CheckDoorLocked implements Action {
    private final String doorId;


    public CheckDoorLocked(String doorId) {
        this.doorId = doorId;
    }


    @Override
    public boolean act(HomeComponent component) {
        if (Objects.equals(component.getId(), doorId)
                && component instanceof Door) {
            return ((Door) component).isLocked();
        }
        return false;
    }
}

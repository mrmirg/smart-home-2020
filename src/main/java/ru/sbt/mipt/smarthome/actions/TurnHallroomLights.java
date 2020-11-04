package ru.sbt.mipt.smarthome.actions;

import ru.sbt.mipt.smarthome.components.HomeComponent;
import ru.sbt.mipt.smarthome.components.Room;

public class TurnHallroomLights implements Action {
    private final String halldoorId;
    private final boolean lightsOn;


    public TurnHallroomLights(String halldoorId, boolean lightsOn) {
        this.halldoorId = halldoorId;
        this.lightsOn = lightsOn;
    }


    @Override
    public boolean act(HomeComponent component) {
        if (
                component instanceof Room
                && ((Room) component).applyAction(new CheckObjectPresence(halldoorId))
        ) {
            return ((Room) component).applyAction(new TurnAllLights(lightsOn));
        }
        return false;
    }
}


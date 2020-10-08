package ru.sbt.mipt.smarthome.actions;

import ru.sbt.mipt.smarthome.Actionable;
import ru.sbt.mipt.smarthome.components.HomeComponent;
import ru.sbt.mipt.smarthome.components.Room;

public class TurnAllLightsInRoomWithComponent implements Action {
    private final String componentId;
    private final boolean lightOn;

    public TurnAllLightsInRoomWithComponent(String componentId, boolean lightOn) {
        this.componentId = componentId;
        this.lightOn = lightOn;
    }

    public boolean act(HomeComponent component) {
        // Если component - это Actionable и не Room
        // то пробрасываем Action во внут. структуру
        if (component instanceof Actionable && !(component instanceof Room)) {
            return ((Actionable) component).applyAction(this);
        }

        // Если component - Room, то
        // > проверим есть ли в комнате наш component (например, halldoor)
        // > если да, включим/выключим в ней весь свет
        if (component instanceof Room) {
            if (((Room) component).applyAction(new CheckPresenceInRoom(componentId))) {
                return ((Room) component).applyAction(new TurnAllLights(lightOn));
            }
        }
        return false;
    }
}

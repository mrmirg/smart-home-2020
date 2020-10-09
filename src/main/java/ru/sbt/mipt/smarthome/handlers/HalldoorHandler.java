package ru.sbt.mipt.smarthome.handlers;

import ru.sbt.mipt.smarthome.actions.OpenCloseDoor;
import ru.sbt.mipt.smarthome.actions.TurnAllLightsInRoomWithComponent;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.DoorClosed;
import ru.sbt.mipt.smarthome.events.DoorOpened;
import ru.sbt.mipt.smarthome.events.SensorEvent;

public class HalldoorHandler implements SensorEventHandler {
    private final SmartHome smartHome;
    private final String halldoorId;

    public HalldoorHandler(SmartHome home, String halldoorId) {
        this.smartHome = home;
        this.halldoorId = halldoorId;
    }

    @Override
    public boolean processEvent(SensorEvent event) {
        if ((event instanceof DoorOpened
                || event instanceof DoorClosed)
                && halldoorId.equals(event.getComponentId())
        ) {
            boolean opened = event instanceof DoorOpened;
            // Если парадная дверь открылась, то включаем свет, и vice versa
            return smartHome.applyAction(new TurnAllLightsInRoomWithComponent(halldoorId, opened)) &&
                    smartHome.applyAction(new OpenCloseDoor(halldoorId, opened)); // и обновляем состояние двери
        }
        return false;
    }
}

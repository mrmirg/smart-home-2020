package ru.sbt.mipt.smarthome.handlers;


import ru.sbt.mipt.smarthome.actions.CheckObjectPresence;
import ru.sbt.mipt.smarthome.actions.TurnAllLights;
import ru.sbt.mipt.smarthome.components.Room;
import ru.sbt.mipt.smarthome.events.DoorClosed;
import ru.sbt.mipt.smarthome.events.DoorOpened;
import ru.sbt.mipt.smarthome.events.SensorEvent;


public class HalldoorHandler implements SensorEventHandler {
    private final Room hallRoom;
    private final String hallDoorId;

    public HalldoorHandler(Room hallRoom, String hallDoorId) {
        if (!hallRoom.applyAction(new CheckObjectPresence(hallDoorId))) {
            throw new IllegalStateException("Hall room does not contain this door!");
        }
        this.hallRoom = hallRoom;
        this.hallDoorId = hallDoorId;
    }


    // Если парадная дверь открылась, то включаем свет, и vice versa
    // этот обработчик только включит свет в холле, за обработку открытия двери отвечает DoorHandler
    @Override
    public boolean processEvent(SensorEvent event) {
        if ((event instanceof DoorOpened
                || event instanceof DoorClosed)
                && hallDoorId.equals(event.getComponentId())
        ) {
            boolean opened = event instanceof DoorOpened;
            return hallRoom.applyAction(new TurnAllLights(opened));
        }
        return false;
    }
}

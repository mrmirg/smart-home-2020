package ru.sbt.mipt.smarthome.handlers;


import ru.sbt.mipt.smarthome.actions.TurnHallroomLights;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.HalldoorOpened;
import ru.sbt.mipt.smarthome.events.SensorEvent;


public class HalldoorHandler implements SensorEventHandler {
    private final SmartHome smartHome;


    public HalldoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    // Если парадная дверь открылась, то включаем свет, и vice versa
    // этот обработчик только включит свет в холле, за обработку открытия двери отвечает DoorHandler
    @Override
    public boolean processEvent(SensorEvent event) {
        if (event instanceof HalldoorOpened) {
            return smartHome.applyAction(
                    new TurnHallroomLights(event.getComponentId(),
                    ((HalldoorOpened) event).isOpened())
            );
        }
        return false;
    }
}

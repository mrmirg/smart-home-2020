package ru.sbt.mipt.smarthome.handlers;


import ru.sbt.mipt.smarthome.actions.TurnLight;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.LightOn;
import ru.sbt.mipt.smarthome.events.SensorEvent;


public class LightHandler implements SensorEventHandler {
    private final SmartHome smartHome;


    public LightHandler(SmartHome home) {
        this.smartHome = home;
    }


    @Override
    public boolean processEvent(SensorEvent event) {
        if (event instanceof LightOn) {
            boolean success = smartHome.applyAction(new TurnLight(
                    event.getComponentId(),
                    ((LightOn) event).isOn()
            ));
            System.out.println(
                    "Turning " +
                    (((LightOn) event).isOn() ? "on" : "off") +
                            " light " +
                            event.getComponentId() + " | " +
                            (success ? "\tSuccess" : "\tFailure")
            );
            return success;
        }
        return false;
    }
}

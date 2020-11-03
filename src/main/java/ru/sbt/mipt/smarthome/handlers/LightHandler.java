package ru.sbt.mipt.smarthome.handlers;


import ru.sbt.mipt.smarthome.actions.TurnLight;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.LightOff;
import ru.sbt.mipt.smarthome.events.LightOn;
import ru.sbt.mipt.smarthome.events.SensorEvent;


public class LightHandler implements SensorEventHandler {
    private final SmartHome smartHome;


    public LightHandler(SmartHome home) {
        this.smartHome = home;
    }


    @Override
    public boolean processEvent(SensorEvent event) {
        if (event instanceof LightOn || event instanceof LightOff) {
            boolean lightOn = event instanceof LightOn;
            boolean success = smartHome.applyAction(new TurnLight(
                    event.getComponentId(),
                    lightOn
            ));
            System.out.println(
                    "Turning " +
                    (lightOn ? "on" : "off") +
                            " light " +
                            event.getComponentId() + " | " +
                            (success ? "\tSuccess" : "\tFailure")
            );
            return success;
        }
        return false;
    }
}

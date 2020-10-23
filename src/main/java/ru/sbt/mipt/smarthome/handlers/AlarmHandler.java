package ru.sbt.mipt.smarthome.handlers;


import ru.sbt.mipt.smarthome.actions.SetAlarmActivated;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.AlarmActivation;
import ru.sbt.mipt.smarthome.events.AlarmDeactivation;
import ru.sbt.mipt.smarthome.events.SensorEvent;


public class AlarmHandler implements SensorEventHandler {
    private final SmartHome smartHome;


    public AlarmHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    @Override
    public boolean processEvent(SensorEvent event) {
        if (event instanceof AlarmActivation || event instanceof AlarmDeactivation) {
            boolean isActivation = event instanceof AlarmActivation;
            String fingerPrint = isActivation ?
                    ((AlarmActivation) event).getFingerPrint() :
                    ((AlarmDeactivation) event).getFingerPrint();
            boolean success = isActivation ?
                    smartHome.applyAction(new SetAlarmActivated(event.getComponentId(), fingerPrint, true)) :
                    smartHome.applyAction(new SetAlarmActivated(event.getComponentId(), fingerPrint, false));

            if (success) {
                System.out.println("Alarm " + event.getComponentId() + " is " + (isActivation ? "activated." : "deactivated."));
            } else {
                System.out.println("Failed to " + (isActivation ? "activate" : "deactivate") + " alarm " + event.getComponentId());
            }
            return success;
        }
        return false;
    }
}

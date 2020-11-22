package ru.sbt.mipt.smarthome.handlers;


import ru.sbt.mipt.smarthome.actions.SetAlarmActivated;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.AlarmActivation;
import ru.sbt.mipt.smarthome.events.SensorEvent;


public class AlarmHandler implements SensorEventHandler {
    private final SmartHome smartHome;


    public AlarmHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    @Override
    public boolean processEvent(SensorEvent event) {
        if (event instanceof AlarmActivation) {
            var alarmActivation = ((AlarmActivation) event);
            String fingerPrint = alarmActivation.getFingerPrint();

            boolean success = smartHome.applyAction(
                    new SetAlarmActivated(
                            event.getComponentId(),
                            fingerPrint,
                            alarmActivation.isActivation()
                    )
            );

            if (success) {
                System.out.println("Alarm "
                        + event.getComponentId() + " is "
                        + (alarmActivation.isActivation() ? "activated." : "deactivated."));
            } else {
                System.out.println("Failed to "
                        + (alarmActivation.isActivation() ? "activate" : "deactivate") +
                        " alarm " +
                        event.getComponentId());
            }
            return success;
        }
        return false;
    }
}

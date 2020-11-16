package ru.sbt.mipt.smarthome.actions;


import ru.sbt.mipt.smarthome.components.HomeComponent;
import ru.sbt.mipt.smarthome.components.alarm.Alarm;


public class CheckAlarmActivated implements Action {
    private final String alarmId;


    public CheckAlarmActivated(String alarmId) {
        this.alarmId = alarmId;
    }


    @Override
    // returns true only if component is alarm and alarm is activated (or emergency)
    public boolean act(HomeComponent component) {
        if (component instanceof Alarm && component.getId().equals(alarmId)) {
            return ((Alarm) component).isActivated() ||
                    ((Alarm) component).isEmergency();
        }
        return false;
    }
}

package ru.sbt.mipt.smarthome.actions;

import ru.sbt.mipt.smarthome.Actionable;
import ru.sbt.mipt.smarthome.components.HomeComponent;
import ru.sbt.mipt.smarthome.components.alarm.Alarm;

public class SetAlarmEmergency implements Action {
    private final String alarmId;

    public SetAlarmEmergency(String alarmId) {
        this.alarmId = alarmId;
    }

    @Override
    public boolean act(HomeComponent component) {
        if (component instanceof Actionable) {
            return ((Actionable) component).applyAction(this);
        }
        if (component instanceof Alarm && component.getId().equals(alarmId)) {
            return ((Alarm) component).setEmergency();
        }
        return false;
    }
}

package ru.sbt.mipt.smarthome.actions;


import ru.sbt.mipt.smarthome.components.HomeComponent;
import ru.sbt.mipt.smarthome.components.alarm.Alarm;


public class SetAlarmActivated implements Action {
    private final String alarmId;
    private final String fingerPrint;
    private final boolean activate;


    public SetAlarmActivated(String alarmId, String fingerPrint, boolean activate) {
        this.alarmId = alarmId;
        this.fingerPrint = fingerPrint;
        this.activate = activate;
    }


    @Override
    public boolean act(HomeComponent component) {
        if (component instanceof Alarm && alarmId.equals(component.getId())) {
            return activate ? ((Alarm) component).getAlarmState().setActivated(fingerPrint) :
                              ((Alarm) component).getAlarmState().setDeactivated(fingerPrint);
        }
        return false;
    }
}

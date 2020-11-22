package ru.sbt.mipt.smarthome.components.alarm;

public class ActivatedState implements AlarmState {
    private final Alarm alarm;


    public ActivatedState(Alarm alarm) {
        this.alarm = alarm;
    }


    @Override
    public boolean setActivated(String alarmFingerprint) {
        return false;
    }


    @Override
    public boolean setDeactivated(String alarmFingerprint) {
        boolean isFPValid = alarm.getFingerPrint().equals(alarmFingerprint);
        if (isFPValid) {
            alarm.setAlarmState(new DeactivatedState(alarm));
        }
        else {
            alarm.setAlarmState(new EmergencyState(alarm));
        }
        return isFPValid;
    }


    @Override
    public boolean setEmergency() {
        alarm.setAlarmState(new EmergencyState(alarm));
        return true;
    }
}

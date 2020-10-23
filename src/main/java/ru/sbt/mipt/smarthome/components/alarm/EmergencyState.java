package ru.sbt.mipt.smarthome.components.alarm;


public class EmergencyState implements AlarmState {
    private final Alarm alarm;

    public EmergencyState(Alarm alarm) {
        if (alarm == null) {
            throw new IllegalArgumentException("Alarm object must be non null");
        }
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
        return isFPValid;
    }


    @Override
    public boolean setEmergency() {
        return false;
    }
}

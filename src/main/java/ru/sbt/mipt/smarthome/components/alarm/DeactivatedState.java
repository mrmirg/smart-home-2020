package ru.sbt.mipt.smarthome.components.alarm;


public class DeactivatedState implements AlarmState {
    private final Alarm alarm;


    public DeactivatedState(Alarm alarm) {
        if (alarm == null) {
            throw new IllegalArgumentException("Alarm object must be non null");
        }
        this.alarm = alarm;
    }


    @Override
    public boolean setActivated(String alarmFingerprint) {
        if (alarmFingerprint == null) {
            throw new IllegalArgumentException("Fingerprint must be non null.");
        }
        alarm.setFingerPrint(alarmFingerprint);
        alarm.setAlarmState(new ActivatedState(alarm));
        return true;
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

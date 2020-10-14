package ru.sbt.mipt.smarthome.components.alarm;


public class EmergencyState implements AlarmState {
    public EmergencyState(Alarm alarm) {
        if (alarm == null) {
            throw new IllegalArgumentException("Alarm object must be non null");
        }
    }


    @Override
    public boolean setActivated(String alarmFingerprint) {
        return false;
    }


    @Override
    public boolean setDeactivated(String alarmFingerprint) {
        return false;
    }


    @Override
    public boolean setEmergency() {
        return false;
    }
}

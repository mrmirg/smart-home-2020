package ru.sbt.mipt.smarthome.components.alarm;


public interface AlarmState {
    boolean setActivated(String alarmFingerprint);
    boolean setDeactivated(String alarmFingerprint);
    boolean setEmergency();
}

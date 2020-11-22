package ru.sbt.mipt.smarthome.components.alarm;

import ru.sbt.mipt.smarthome.components.HomeComponent;

public class Alarm implements HomeComponent {
    private final String id;
    private String fingerPrint;
    private AlarmState alarmState;


    String getFingerPrint() {
        return fingerPrint;
    }


    public boolean isActivated() {
        return alarmState instanceof ActivatedState;
    }


    public boolean isEmergency() {
        return alarmState instanceof EmergencyState;
    }


    public boolean isDeactivated() {
        return alarmState instanceof DeactivatedState;
    }


    void setFingerPrint(String fingerPrint) {
        if (fingerPrint == null) {
            throw new IllegalArgumentException();
        }
        this.fingerPrint = fingerPrint;
    }


    public Alarm(String id) {
        this.alarmState = new DeactivatedState(this);
        this.id = id;
    }


    public boolean setActivated(String fingerprint) {
        return getAlarmState().setActivated(fingerprint);
    }


    public boolean setEmergency() {
        return getAlarmState().setEmergency();
    }


    public boolean setDeactivated(String fingerprint) {
        return getAlarmState().setDeactivated(fingerprint);
    }


    void setAlarmState(AlarmState state) {
        alarmState = state;
    }


    AlarmState getAlarmState() {
        return alarmState;
    }


    @Override
    public String getId() {
        return id;
    }
}

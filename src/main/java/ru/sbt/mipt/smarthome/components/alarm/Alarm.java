package ru.sbt.mipt.smarthome.components.alarm;

import ru.sbt.mipt.smarthome.components.HomeComponent;

public class Alarm implements HomeComponent, AlarmState {
    private final String id;
    private String fingerPrint;
    private AlarmState alarmState;
    private boolean superMegaLoudAlarmNotifier;


    void enableAlarmNotifier(boolean enable) {
        superMegaLoudAlarmNotifier = enable;
    }


    boolean isAlarmNotifierEnabled() {
        return superMegaLoudAlarmNotifier;
    }


    String getFingerPrint() {
        return fingerPrint;
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
        this.superMegaLoudAlarmNotifier = false;
    }


    void setAlarmState(AlarmState state) {
        alarmState = state;
    }


    public AlarmState getAlarmState() {
        return alarmState;
    }


    @Override
    public boolean setActivated(String alarmFingerprint) {
        return alarmState.setActivated(alarmFingerprint);
    }


    @Override
    public boolean setDeactivated(String alarmFingerprint) {
        boolean success = alarmState.setDeactivated(alarmFingerprint);
        if (success) {
            enableAlarmNotifier(false);
        }
        return success;
    }


    @Override
    public boolean setEmergency() {
        boolean success = alarmState.setEmergency();
        if (success) {
            enableAlarmNotifier(true);
        }
        return success;
    }


    @Override
    public String getId() {
        return id;
    }
}

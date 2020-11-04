package ru.sbt.mipt.smarthome.components.alarm;

import ru.sbt.mipt.smarthome.components.HomeComponent;

public class Alarm implements HomeComponent {
    private final String id;
    private String fingerPrint;
    private AlarmState alarmState;


    String getFingerPrint() {
        return fingerPrint;
    }




    void setFingerPrint(String fingerPrint) {
        if (fingerPrint == null) {
            throw new IllegalArgumentException();
        }
        this.fingerPrint = fingerPrint;
    }


    public AlarmState getState() {
        return alarmState;
    }


    public Alarm(String id) {
        this.alarmState = new DeactivatedState(this);
        this.id = id;
    }


    void setAlarmState(AlarmState state) {
        alarmState = state;
    }


    public AlarmState getAlarmState() {
        return alarmState;
    }


    @Override
    public String getId() {
        return id;
    }
}

package ru.sbt.mipt.smarthome.events;

public class AlarmDeactivation implements SensorEvent {
    private final String alarmId;
    private final String fingerPrint;


    public AlarmDeactivation(String alarmId, String fingerPrint) {
        if (alarmId == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        this.alarmId = alarmId;
        this.fingerPrint = fingerPrint;
    }


    public String getFingerPrint() {
        return fingerPrint;
    }


    @Override
    public String getComponentId() {
        return alarmId;
    }
}
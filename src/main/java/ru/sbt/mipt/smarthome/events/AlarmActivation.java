package ru.sbt.mipt.smarthome.events;


public class AlarmActivation implements SensorEvent {
    private final String alarmId;
    private final String fingerPrint;
    private final boolean activated;


    public AlarmActivation(String alarmId, String fingerPrint, boolean activated) {
        if (alarmId == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        this.alarmId = alarmId;
        this.fingerPrint = fingerPrint;
        this.activated = activated;
    }


    public boolean isActivation() {
        return activated;
    }


    public String getFingerPrint() {
        return fingerPrint;
    }


    @Override
    public String getComponentId() {
        return alarmId;
    }
}

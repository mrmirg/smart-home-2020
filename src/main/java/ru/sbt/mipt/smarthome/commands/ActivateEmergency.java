package ru.sbt.mipt.smarthome.commands;

import ru.sbt.mipt.smarthome.actions.SetAlarmEmergency;
import ru.sbt.mipt.smarthome.components.SmartHome;


public class ActivateEmergency implements SensorCommand {
    private final SmartHome smartHome;
    private final String alarmId;
    private final String fingerprint;


    public ActivateEmergency(SmartHome smartHome, String alarmId, String fingerprint) {
        this.smartHome = smartHome;
        this.alarmId = alarmId;
        this.fingerprint = fingerprint;
    }


    @Override
    public boolean execute() {
        return smartHome.applyAction(new SetAlarmEmergency(alarmId));
    }
}

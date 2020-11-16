package ru.sbt.mipt.smarthome.commands;


import ru.sbt.mipt.smarthome.actions.SetAlarmActivated;
import ru.sbt.mipt.smarthome.components.SmartHome;


public class ActivateAlarm implements SensorCommand {
    private final SmartHome smartHome;
    private final boolean activate;
    private final String alarmId;
    private final String fingerprint;


    public ActivateAlarm(SmartHome smartHome, boolean activate, String alarmId, String fingerprint) {
        this.smartHome = smartHome;
        this.activate = activate;
        this.alarmId = alarmId;
        this.fingerprint = fingerprint;
    }


    @Override
    public boolean execute() {
        return smartHome.applyAction(new SetAlarmActivated(alarmId, fingerprint, activate));
    }
}

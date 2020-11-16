package ru.sbt.mipt.smarthome.handlers;


import ru.sbt.mipt.smarthome.actions.CheckAlarmActivated;
import ru.sbt.mipt.smarthome.actions.SetAlarmEmergency;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.AlarmActivation;
import ru.sbt.mipt.smarthome.events.SensorEvent;


public class AlarmDecorator implements SensorEventHandler {
    private final SensorEventHandler handler;
    private final SmartHome smartHome;
    private final String alarmId;
    private static boolean muteEvents = false;


    public AlarmDecorator(SmartHome smartHome, String alarmId, SensorEventHandler handler) {
        if (handler == null) {
            throw new IllegalArgumentException("Handler must be non null");
        }
        this.handler = handler;
        this.smartHome = smartHome;
        this.alarmId = alarmId;
    }


    @Override
    public boolean processEvent(SensorEvent event) {
        if (event instanceof AlarmActivation
                && handler instanceof AlarmHandler
                && handler.processEvent(event)) {
            muteEvents = false;
            return true;
        }
        if (muteEvents) {
            return false;
        }
        if (smartHome.applyAction(new CheckAlarmActivated(alarmId))) {
            smartHome.applyAction(new SetAlarmEmergency(alarmId));
            System.out.println("Alarm " + alarmId + " | Illegal access detected! Sending sms..");
            muteEvents = true;
            return false;
        }
        return handler.processEvent(event);
    }
}

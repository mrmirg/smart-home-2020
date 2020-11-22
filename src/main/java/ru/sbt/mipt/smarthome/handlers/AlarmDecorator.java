package ru.sbt.mipt.smarthome.handlers;


import ru.sbt.mipt.smarthome.Notifier;
import ru.sbt.mipt.smarthome.SmsNotifier;
import ru.sbt.mipt.smarthome.actions.CheckAlarmActivated;
import ru.sbt.mipt.smarthome.actions.CheckAlarmEmergency;
import ru.sbt.mipt.smarthome.actions.SetAlarmEmergency;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.AlarmActivation;
import ru.sbt.mipt.smarthome.events.SensorEvent;


public class AlarmDecorator implements SensorEventHandler {
    private final SensorEventHandler compositeHandler;
    private final SmartHome smartHome;
    private final String alarmId;
    private final Notifier notifier = new SmsNotifier();


    public AlarmDecorator(SmartHome smartHome, String alarmId, SensorEventHandler handler) {
        this.compositeHandler = handler;
        this.smartHome = smartHome;
        this.alarmId = alarmId;
    }


    @Override
    public boolean processEvent(SensorEvent event) {
        if (event instanceof AlarmActivation
            && !((AlarmActivation) event).isActivation()) {
            return compositeHandler.processEvent(event);
        }

        if (smartHome.applyAction(new CheckAlarmActivated(alarmId))) {
            smartHome.applyAction(new SetAlarmEmergency(alarmId));
            notifier.doNotify("Illegal access detected! Emergency state activated.");
            return false;
        }
        if (!smartHome.applyAction(new CheckAlarmEmergency(alarmId))) {
            return compositeHandler.processEvent(event);
        }
        return false;
    }
}

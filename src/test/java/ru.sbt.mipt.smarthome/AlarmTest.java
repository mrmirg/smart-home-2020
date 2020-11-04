package ru.sbt.mipt.smarthome;


import org.junit.jupiter.api.Test;
import ru.sbt.mipt.smarthome.components.*;
import ru.sbt.mipt.smarthome.components.alarm.ActivatedState;
import ru.sbt.mipt.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.smarthome.components.alarm.DeactivatedState;
import ru.sbt.mipt.smarthome.components.alarm.EmergencyState;
import ru.sbt.mipt.smarthome.events.AlarmActivation;
import ru.sbt.mipt.smarthome.handlers.AlarmHandler;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AlarmTest {
    @Test
    public void handleExistingAlarm() {
        // given
        Door door = new Door("2", false);
        Light light = new Light("1", false);

        HomeComponent atticRoom = new Room("attic", "attic", Arrays.asList(door, light));

        Alarm alarm = new Alarm("alarm0");
        SmartHome smartHome = new SmartHome("strangehome", Arrays.asList(atticRoom, alarm));

        AlarmHandler alarmHandler = new AlarmHandler(smartHome);

        // when
        boolean successActivated = alarmHandler
                .processEvent(new AlarmActivation("alarm0", "very_safe_password", true));
        boolean isAlarmActivated = alarm.getAlarmState() instanceof ActivatedState;
        boolean successDeactivated = alarmHandler
                .processEvent(new AlarmActivation("alarm0", "very_safe_password", false));
        boolean isAlarmDeactivated = alarm.getAlarmState() instanceof DeactivatedState;

        // then
        assertTrue(successActivated);
        assertTrue(isAlarmActivated);
        assertTrue(successDeactivated);
        assertTrue(isAlarmDeactivated);
    }


    @Test
    public void handleNonExistingAlarm() {
        // given
        Door door = new Door("2", false);
        Light light = new Light("1", false);

        HomeComponent atticRoom = new Room("attic", "attic", Arrays.asList(door, light));

        Alarm alarm = new Alarm("alarm0");
        SmartHome smartHome = new SmartHome("strangehome", Arrays.asList(atticRoom, alarm));

        AlarmHandler alarmHandler = new AlarmHandler(smartHome);

        //when
        boolean successFalseActivated = alarmHandler
                .processEvent(new AlarmActivation("alarm_not_0", "very_safe_password", true));
        boolean isFalseActivated = alarm.getAlarmState() instanceof ActivatedState;
        boolean successActivated = alarmHandler
                .processEvent(new AlarmActivation("alarm0", "very_safe_password", true));
        boolean isActivated = alarm.getAlarmState() instanceof ActivatedState;

        boolean successFalseDeactivated = alarmHandler
                .processEvent(new AlarmActivation("alarm_not_0", "very_safe_password", false));
        boolean isFalseDeactivated = alarm.getAlarmState() instanceof DeactivatedState;

        // alarm deactivation with wrong id must not affect existing alarm
        boolean isEmergency = alarm.getAlarmState() instanceof EmergencyState;

        //then
        assertFalse(successFalseActivated);
        assertFalse(isFalseActivated);
        assertTrue(successActivated);
        assertTrue(isActivated);
        assertFalse(successFalseDeactivated);
        assertFalse(isFalseDeactivated);
        assertFalse(isEmergency);
    }


    @Test
    public void invalidDeactivation() {
        // given
        Door door = new Door("2", false);
        Light light = new Light("1", false);

        HomeComponent atticRoom = new Room("attic", "attic", Arrays.asList(door, light));

        Alarm alarm = new Alarm("alarm0");
        SmartHome smartHome = new SmartHome("strangehome", Arrays.asList(atticRoom, alarm));

        AlarmHandler alarmHandler = new AlarmHandler(smartHome);

        // when
        boolean successActivated = alarmHandler
                .processEvent(new AlarmActivation("alarm0", "very_safe_password", true));
        boolean successDeactivated = alarmHandler
                .processEvent(new AlarmActivation("alarm0", "not_very_safe_password", false));
        boolean isEmergency = alarm.getAlarmState() instanceof EmergencyState;
        boolean successDeactivated1 = alarmHandler
                .processEvent(new AlarmActivation("alarm0", "not_very_safe_password", false));
        boolean isEmergency1 = alarm.getAlarmState() instanceof EmergencyState;

        boolean successDeactivated2 = alarmHandler
                .processEvent(new AlarmActivation("alarm0", "very_safe_password", false));
        boolean isEmergency2 = alarm.getAlarmState() instanceof EmergencyState;


        // then
        assertTrue(successActivated);
        assertFalse(successDeactivated);
        assertFalse(successDeactivated1);
        assertTrue(successDeactivated2);
        assertTrue(isEmergency);
        assertTrue(isEmergency1);
        assertFalse(isEmergency2);
    }
}

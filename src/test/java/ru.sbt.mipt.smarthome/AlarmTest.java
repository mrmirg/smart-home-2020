package ru.sbt.mipt.smarthome;


import org.junit.jupiter.api.Test;
import ru.sbt.mipt.smarthome.components.*;
import ru.sbt.mipt.smarthome.components.alarm.*;
import ru.sbt.mipt.smarthome.events.AlarmActivation;
import ru.sbt.mipt.smarthome.events.AlarmDeactivation;
import ru.sbt.mipt.smarthome.handlers.AlarmHandler;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


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
                .processEvent(new AlarmActivation("alarm0", "very_safe_password"));
        boolean isAlarmActivated = alarm.getAlarmState() instanceof ActivatedState;
        boolean successDeactivated = alarmHandler
                .processEvent(new AlarmDeactivation("alarm0", "very_safe_password"));
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
                .processEvent(new AlarmActivation("alarm_not_0", "very_safe_password"));
        boolean isFalseActivated = alarm.getAlarmState() instanceof ActivatedState;
        boolean successActivated = alarmHandler
                .processEvent(new AlarmActivation("alarm0", "very_safe_password"));
        boolean isActivated = alarm.getAlarmState() instanceof ActivatedState;

        boolean successFalseDeactivated = alarmHandler
                .processEvent(new AlarmDeactivation("alarm_not_0", "very_safe_password"));
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
                .processEvent(new AlarmActivation("alarm0", "very_safe_password"));
        boolean successDeactivated = alarmHandler
                .processEvent(new AlarmDeactivation("alarm0", "not_very_safe_password"));
        boolean isEmergency = alarm.getAlarmState() instanceof EmergencyState;
        boolean successDeactivated1 = alarmHandler
                .processEvent(new AlarmDeactivation("alarm0", "not_very_safe_password"));
        boolean isEmergency1 = alarm.getAlarmState() instanceof EmergencyState;

        boolean successDeactivated2 = alarmHandler
                .processEvent(new AlarmDeactivation("alarm0", "very_safe_password"));
        boolean isEmergency2 = alarm.getAlarmState() instanceof EmergencyState;


        // then
        assertTrue(successActivated);
        assertFalse(successDeactivated);
        assertFalse(successDeactivated1);
        assertFalse(successDeactivated2);
        assertTrue(isEmergency);
        assertTrue(isEmergency1);
        assertTrue(isEmergency2);
    }
}

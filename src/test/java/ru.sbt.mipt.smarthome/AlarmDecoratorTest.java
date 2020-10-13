package ru.sbt.mipt.smarthome;


import org.junit.jupiter.api.Test;
import ru.sbt.mipt.smarthome.actions.SetAlarmActivated;
import ru.sbt.mipt.smarthome.components.*;
import ru.sbt.mipt.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.smarthome.components.alarm.DeactivatedState;
import ru.sbt.mipt.smarthome.components.alarm.EmergencyState;
import ru.sbt.mipt.smarthome.events.DoorOpened;
import ru.sbt.mipt.smarthome.events.LightOn;
import ru.sbt.mipt.smarthome.handlers.SensorEventHandler;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AlarmDecoratorTest {
    @Test
    public void whenAlarmDeactivated() {
        // given
        Door door = new Door("2", false);
        Light light = new Light("1", false);

        HomeComponent atticRoom = new Room("attic", "attic", Arrays.asList(door, light));

        Alarm alarm = new Alarm("alarm0");
        SmartHome smartHome = new SmartHome("strangehome", Arrays.asList(atticRoom, alarm));

        SensorEventHandler eventHandler = new CompositeHandlerBuilder(smartHome).buildDefaultWithAlarm("alarm0");

        // when
        boolean doorOpened = eventHandler
                .processEvent(new DoorOpened("2"));
        boolean alarmDeactivated = alarm.getAlarmState() instanceof DeactivatedState;
        boolean doorActuallyOpened = door.isOpened();

        // then
        assertTrue(doorOpened);
        assertTrue(alarmDeactivated);
        assertTrue(doorActuallyOpened);
    }


    @Test
    public void whenAlarmActivated() {
        // given
        Door door = new Door("2", false);
        Light light = new Light("1", false);

        HomeComponent atticRoom = new Room("attic", "attic", Arrays.asList(door, light));

        Alarm alarm = new Alarm("alarm0");
        SmartHome smartHome = new SmartHome("strangehome", Arrays.asList(atticRoom, alarm));

        SensorEventHandler eventHandler = new CompositeHandlerBuilder(smartHome).buildDefaultWithAlarm("alarm0");
        smartHome.applyAction(new SetAlarmActivated("alarm0", "very_safe_password", true));

        // when
        boolean doorOpened = eventHandler
                .processEvent(new DoorOpened("2"));
        boolean alarmEmergency = alarm.getAlarmState() instanceof EmergencyState;
        boolean doorActuallyOpened = door.isOpened();

        boolean lightOn = eventHandler
                .processEvent(new LightOn("1"));
        boolean lightActuallyOn = light.isOn();

        // then
        assertFalse(doorOpened);
        assertTrue(alarmEmergency);
        assertFalse(doorActuallyOpened);
        assertFalse(lightOn);
        assertFalse(lightActuallyOn);
    }
}

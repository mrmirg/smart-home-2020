package ru.sbt.mipt.smarthome;


import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import rc.RemoteControlImpl;
import ru.sbt.mipt.smarthome.actions.CheckAllLightsOff;
import ru.sbt.mipt.smarthome.commands.*;
import ru.sbt.mipt.smarthome.components.alarm.ActivatedState;
import ru.sbt.mipt.smarthome.components.alarm.DeactivatedState;
import ru.sbt.mipt.smarthome.components.alarm.EmergencyState;
import ru.sbt.mipt.smarthome.config.SmartHomeConfiguration;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RemoteControlTest {
    private final SmartHomeConfiguration config = new AnnotationConfigApplicationContext(
            SmartHomeConfiguration.class)
            .getBean(SmartHomeConfiguration.class);
    private final String rcId = "remote1";


    @Test
    public void ActivateAlarmTest() throws IOException {
        // given
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);

        var fingerprint = "qwerty";
        var remoteControl = new RemoteControlImpl(
                Map.of(rcId, Map.of(
                        "A", new ActivateAlarm(
                                config.smartHome(),
                                true,
                                config.alarm().getId(),
                                fingerprint
                        ),
                        "B", new ActivateAlarm(
                                config.smartHome(),
                                false,
                                config.alarm().getId(),
                                fingerprint
                        ),
                        "C", new ActivateEmergency(
                                config.smartHome(),
                                config.alarm().getId(),
                                fingerprint
                        ),
                        "D", new ActivateAlarm( //invalid fingerprint
                                config.smartHome(),
                                false,
                                config.alarm().getId(),
                                "ytrewq"
                        ))
                )
        );

        // when
        remoteControl.onButtonPressed("A", rcId);
        //then
        assertTrue(config.alarm().getState() instanceof ActivatedState);

        // when
        remoteControl.onButtonPressed("C", rcId);
        // then
        assertTrue(config.alarm().getState() instanceof EmergencyState);

        // when
        remoteControl.onButtonPressed("D", rcId);
        // then
        assertTrue(config.alarm().getState() instanceof EmergencyState);

        // when
        remoteControl.onButtonPressed("B", rcId);
        // then
        assertTrue(config.alarm().getState() instanceof DeactivatedState);
    }


    @Test
    public void OpenHalldoorTest() throws IOException {
        // given
        var remoteControl = new RemoteControlImpl(
                Map.of(rcId, Map.of(
                        "A", new OpenHalldoor(
                                config.smartHome(),
                                true,
                                config.hallDoor().getId()
                        ),
                        "B", new OpenHalldoor(
                                config.smartHome(),
                                false,
                                config.hallDoor().getId()
                        )
                )
        ));

        // when
        remoteControl.onButtonPressed("A", rcId);
        // then
        assertTrue(config.hallDoor().isOpened());

        // when
        remoteControl.onButtonPressed("B", rcId);
        // then
        assertFalse(config.hallDoor().isOpened());
    }


    @Test
    public void TurnHallLightTest() throws IOException {
        // given
        var remoteControl = new RemoteControlImpl(
                Map.of(rcId, Map.of(
                        "A", new TurnHallLight(
                                config.smartHome(),
                                true,
                                config.hallDoor().getId()
                        ),
                        "B", new TurnHallLight(
                                config.smartHome(),
                                false,
                                config.hallDoor().getId()
                        ))
                )
        );

        // when
        remoteControl.onButtonPressed("A", rcId);
        // then
        assertTrue(
                config.hallLight_1().isOn() &&
                config.hallLight_2().isOn() &&
                config.hallLight_3().isOn() &&
                !config.light1_1().isOn() &&
                !config.light3_1().isOn() &&
                !config.light2_1().isOn()
        );

        // when
        remoteControl.onButtonPressed("B", rcId);
        // then
        assertTrue(config.smartHome().applyAction(new CheckAllLightsOff()));
    }


    @Test
    public void TurnAllLightsTest() throws IOException {
        // given
        var remoteControl = new RemoteControlImpl(
                Map.of(rcId, Map.of(
                        "A", new TurnAllLights(
                                config.smartHome(),
                                true
                        ),
                        "B", new TurnAllLights(
                                config.smartHome(),
                                false
                        ))
                )
        );

        // when
        remoteControl.onButtonPressed("A", rcId);
        // then
        assertFalse(config.smartHome().applyAction(new CheckAllLightsOff()));

        // when
        remoteControl.onButtonPressed("B", rcId);
        // then
        assertTrue(config.smartHome().applyAction(new CheckAllLightsOff()));
    }

}

package ru.sbt.mipt.smarthome.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.smarthome.actions.CheckAlarmActivated;
import ru.sbt.mipt.smarthome.actions.CheckAllLightsOff;
import ru.sbt.mipt.smarthome.actions.CheckDoorOpened;
import ru.sbt.mipt.smarthome.commands.*;

import java.io.IOException;


@Configuration
@Import({SmartHomeConfiguration.class})
public class ControlCommandConfig {
    private final String fingerprint = "qwerty";


    @Bean
    ActivateAlarm activateAlarm(SmartHomeConfiguration config) throws IOException {
        return new ActivateAlarm(
                config.smartHome(),
                !config.smartHome().applyAction(
                        new CheckAlarmActivated(
                                config.alarm().getId()
                        )),
                config.alarm().getId(),
                fingerprint
        );
    }


    @Bean
    ActivateEmergency activateEmergency(SmartHomeConfiguration config) throws IOException {
        return new ActivateEmergency(
                config.smartHome(),
                config.alarm().getId(),
                fingerprint
        );
    }


    @Bean
    TurnHallLight turnHallLight(SmartHomeConfiguration config) throws IOException {
        return new TurnHallLight(
                config.smartHome(),
                !config.hallRoom().applyAction(new CheckAllLightsOff()),
                config.alarm().getId()
        );
    }


    @Bean
    OpenHalldoor openHalldoor(SmartHomeConfiguration config) throws IOException {
        return new OpenHalldoor(
                config.smartHome(),
                !config.smartHome().applyAction(
                        new CheckDoorOpened(
                                config.hallDoor().getId()
                        )
                ),
                config.hallDoor().getId()
        );
    }


    @Bean
    TurnAllLights turnAllLights(SmartHomeConfiguration config) throws IOException {
        return new TurnAllLights(
                config.smartHome(),
                true
        );
    }
}

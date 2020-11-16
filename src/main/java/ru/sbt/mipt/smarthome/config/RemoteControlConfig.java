package ru.sbt.mipt.smarthome.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rc.RemoteControl;
import rc.RemoteControlImpl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.smarthome.commands.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;


@Configuration
@Import({SmartHomeConfiguration.class, ControlCommandConfig.class})
public class RemoteControlConfig {
    @Bean
    RemoteControlRegistry remoteControlRegistry(Collection<RemoteControl> remoteControls) {
        return new RemoteControlRegistry(remoteControls);
    }


    @Bean
    RemoteControl remoteControl(ActivateAlarm activateAlarm,
                                ActivateEmergency activateEmergency,
                                TurnAllLights turnAllLights,
                                TurnHallLight turnHallLight,
                                OpenHalldoor openHalldoor) throws IOException {
        var rcId = "rc0";
        var fingerprint = "qwerty";

        var controlMapping = Map.of(
                "A", activateAlarm,
                "B", activateEmergency,
                "C", turnAllLights,
                "D", turnHallLight,
                "1", openHalldoor
        );
        return new RemoteControlImpl(rcId, controlMapping);
    }
}

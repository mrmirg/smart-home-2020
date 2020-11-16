package ru.sbt.mipt.smarthome.config;


import com.coolcompany.smarthome.events.EventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.smarthome.handlers.*;

import java.io.IOException;


@Configuration
@Import({SmartHomeConfiguration.class})
public class HandlerConfiguration {

    @Bean
    public EventHandler doorHandler(SmartHome smartHome, Alarm alarm) throws IOException {
        return new EventHandlerAdapter(
                new AlarmDecorator(
                        smartHome,
                        alarm.getId(),
                        new DoorHandler(smartHome)
                ));
    }


    @Bean
    public EventHandler lightHandler(SmartHome smartHome, Alarm alarm) throws IOException {
        return new EventHandlerAdapter(
                new AlarmDecorator(
                        smartHome,
                        alarm.getId(),
                        new LightHandler(smartHome)
                ));
    }


    @Bean
    public EventHandler alarmHandler(SmartHome smartHome, Alarm alarm) throws IOException {
        return new EventHandlerAdapter(
                new AlarmDecorator(
                        smartHome,
                        alarm.getId(),
                        new AlarmHandler(smartHome)
                )
        );
    }


    @Bean
    public EventHandler halldoorHandler(SmartHome smartHome, Alarm alarm) throws IOException {
        return new EventHandlerAdapter(
                new AlarmDecorator(
                        smartHome,
                        alarm.getId(),
                        new HalldoorHandler(smartHome)
                )
        );
    }
}

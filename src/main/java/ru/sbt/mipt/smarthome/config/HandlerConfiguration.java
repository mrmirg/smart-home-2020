package ru.sbt.mipt.smarthome.config;


import com.coolcompany.smarthome.events.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.smarthome.handlers.*;

import java.io.IOException;


@Configuration
@Import({SmartHomeConfiguration.class})
public class HandlerConfiguration {
    @Autowired private SmartHomeConfiguration smartHomeConfiguration;


    @Bean
    public EventHandler doorHandler() throws IOException {
        return new EventHandlerAdapter(
                new AlarmDecorator(
                        smartHomeConfiguration.smartHome(),
                        smartHomeConfiguration.alarm().getId(),
                        new DoorHandler(smartHomeConfiguration.smartHome())
                ));
    }


    @Bean
    public EventHandler lightHandler() throws IOException {
        return new EventHandlerAdapter(
                new AlarmDecorator(
                        smartHomeConfiguration.smartHome(),
                        smartHomeConfiguration.alarm().getId(),
                        new LightHandler(smartHomeConfiguration.smartHome())
                ));
    }


    @Bean
    public EventHandler alarmHandler() throws IOException {
        return new EventHandlerAdapter(
                new AlarmHandler(smartHomeConfiguration.smartHome())
        );
    }


    @Bean
    public EventHandler halldoorHandler() throws IOException {
        return new EventHandlerAdapter(
                new AlarmDecorator(
                        smartHomeConfiguration.smartHome(),
                        smartHomeConfiguration.alarm().getId(),
                        new HalldoorHandler(smartHomeConfiguration.smartHome())
                )
        );
    }
}

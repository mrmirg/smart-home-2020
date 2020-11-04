package ru.sbt.mipt.smarthome.config;


import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.IOException;


@Configuration
@Import({HandlerConfiguration.class})
public class AppConfiguration {
    @Autowired HandlerConfiguration handlerConfiguration;

    @Bean
    public SensorEventsManager sensorEventsManager() throws IOException {
        var eventManager = new SensorEventsManager();

        eventManager.registerEventHandler(handlerConfiguration.alarmHandler());
        eventManager.registerEventHandler(handlerConfiguration.lightHandler());
        eventManager.registerEventHandler(handlerConfiguration.doorHandler());
        eventManager.registerEventHandler(handlerConfiguration.halldoorHandler());

        return eventManager;
    }
}

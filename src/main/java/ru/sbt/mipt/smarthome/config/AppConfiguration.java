package ru.sbt.mipt.smarthome.config;


import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Collection;


@Configuration
@Import({HandlerConfiguration.class})
public class AppConfiguration {

    @Bean
    public SensorEventsManager sensorEventsManager(Collection<EventHandler> handlers) {
        var eventManager = new SensorEventsManager();
        for (EventHandler handler : handlers) {
            eventManager.registerEventHandler(handler);
        }
        return eventManager;
    }
}

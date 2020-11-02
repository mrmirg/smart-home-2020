package ru.sbt.mipt.smarthome.config;


import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.handlers.DoorHandler;
import ru.sbt.mipt.smarthome.handlers.EventHandlerAdapter;


@Configuration
@Import({HandlerConfiguration.class, SmartHomeConfiguration.class})
public class AppConfiguration {
    @Bean
    public SensorEventsManager sensorEventsManager() {
        var eventManager = new SensorEventsManager();

        eventManager.registerEventHandler();
    }
}

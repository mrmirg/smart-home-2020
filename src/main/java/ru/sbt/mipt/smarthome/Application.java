package ru.sbt.mipt.smarthome;


import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.config.AppConfiguration;
import ru.sbt.mipt.smarthome.events.RandomEventGeneratorBuilderWithIntIds;
import ru.sbt.mipt.smarthome.handlers.*;

import java.io.IOException;
import java.util.Arrays;


public class Application {
    public static void main(String... args) throws IOException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }
}

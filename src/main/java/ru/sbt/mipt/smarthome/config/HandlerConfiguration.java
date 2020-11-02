package ru.sbt.mipt.smarthome.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.handlers.*;


@Configuration
@Import({ComponentConfiguration.class, SmartHomeConfiguration.class})
public class HandlerConfiguration {
    @Autowired private SmartHome smartHome;
    @Autowired private String alarmId;


    @Bean
    @Scope("prototype")
    public SensorEventHandler doorHandler() {
        return new AlarmDecorator(smartHome, alarmId, new DoorHandler(smartHome));
    }


    @Bean
    @Scope("prototype")
    public SensorEventHandler lightHandler() {
        return new AlarmDecorator(smartHome, alarmId, new LightHandler(smartHome));
    }


    @Bean
    @Scope("prototype")
    public SensorEventHandler alarmHandler() {
        return new AlarmHandler(smartHome);
    }
}

package ru.sbt.mipt.smarthome.config;


import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.smarthome.events.DoorLocked;
import ru.sbt.mipt.smarthome.events.DoorOpened;
import ru.sbt.mipt.smarthome.events.LightOn;
import ru.sbt.mipt.smarthome.events.SensorEvent;
import ru.sbt.mipt.smarthome.handlers.*;
import java.util.function.Function;
import java.util.Collection;


@Configuration
@Import({SmartHomeConfiguration.class})
public class HandlerConfiguration {
    @Bean
    @Lazy
    public EventHandler compositeHandlerWithAlarm(
            SmartHome smartHome,
            Alarm alarm,
            Function<CCSensorEvent, SensorEvent> eventMapping,
            Collection<SensorEventHandler> handlers) {
        return new EventHandlerAdapter(
                new AlarmDecorator(
                        smartHome,
                        alarm.getId(),
                        new CompositeEventHandler(handlers)
                ),
                eventMapping
        );
    }


    @Bean
    public Function<CCSensorEvent, SensorEvent> eventMapping() {
        return (CCSensorEvent event) -> {
            String id = event.getObjectId();
            switch (event.getEventType()) {
                case "LightIsOn"    : return new LightOn(id, true);
                case "LightIsOff"   : return new LightOn(id, false);
                case "DoorIsOpen"   : return new DoorOpened(id, true);
                case "DoorIsClosed" : return new DoorOpened(id, false);
                case "DoorIsLocked" : return new DoorLocked(id, true);
                case "DoorIsUnlocked" : return new DoorLocked(id, false);
                default:
                    throw new IllegalArgumentException("Unknown event type!");
            }
        };
    }


    @Bean
    public SensorEventHandler doorHandler(SmartHome smartHome) {
        return new DoorHandler(smartHome);
    }


    @Bean
    public SensorEventHandler lightHandler(SmartHome smartHome) {
        return new LightHandler(smartHome);
    }


    @Bean
    public SensorEventHandler alarmHandler(SmartHome smartHome) {
        return new AlarmHandler(smartHome);
    }


    @Bean
    public SensorEventHandler halldoorHandler(SmartHome smartHome) {
        return new HalldoorHandler(smartHome);
    }
}

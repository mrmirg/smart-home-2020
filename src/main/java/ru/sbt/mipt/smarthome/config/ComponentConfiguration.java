package ru.sbt.mipt.smarthome.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.sbt.mipt.smarthome.components.Door;
import ru.sbt.mipt.smarthome.components.Light;
import ru.sbt.mipt.smarthome.components.Room;
import ru.sbt.mipt.smarthome.components.alarm.Alarm;


@Configuration
public class ComponentConfiguration {
    private class SimpleIdGenerator {
        private int currIntId = -1;

        public String generate() {
            currIntId++;
            return String.valueOf(currIntId);
        }
    }


    public String alarmId = "alarm";


    @Bean
    public SimpleIdGenerator idGenerator() {
        return this.new SimpleIdGenerator();
    }


    @Bean
    @Scope("prototype")
    public Room room() {
        // ? return new Room(Arrays.asList());
    }


    @Bean
    @Scope("prototype")
    public Door door() {
        return new Door(idGenerator().generate(), false);
    }


    @Bean
    @Scope("prototype")
    public Light light() {
        return new Light(idGenerator().generate(), false);
    }


    @Bean
    @Scope("prototype")
    public Alarm alarm() {
        return new Alarm(alarmId);
    }



}

package ru.sbt.mipt.smarthome.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.smarthome.SmartHomeIO;
import ru.sbt.mipt.smarthome.SmartHomeJsonIO;
import ru.sbt.mipt.smarthome.components.Door;
import ru.sbt.mipt.smarthome.components.Light;
import ru.sbt.mipt.smarthome.components.Room;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.components.alarm.Alarm;

import java.io.IOException;
import java.util.Arrays;


@Configuration
public class SmartHomeConfiguration {
    private final String homePath = "src/resources/smarthome.json";

    @Bean public SmartHome smartHome() throws IOException {
        SmartHome smartHome = null;

        try {
            smartHome = smartHomeIO().readHome(homePath);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

        System.out.println("??1");
        if (smartHome == null) {
            smartHome = new SmartHome("smart", Arrays.asList(
                    room1(),
                    room2(),
                    room3(),
                    hallRoom(),
                    alarm()
            ));
            System.out.println("??2");
        }
        System.out.println("??3");
        return smartHome;
    }

    @Bean public Alarm alarm() {
        return new Alarm("alarm0");
    }

    @Bean public Room room1() {
        return new Room("room1", "r1", Arrays.asList(
                door1(),
                light1_1(),
                light1_2()
        ));
    }

    @Bean public Room room2() {
        return new Room("room2", "r2", Arrays.asList(
                door2(),
                light2_1()
        ));
    }

    @Bean public Room room3() {
        return new Room("room3", "r3", Arrays.asList(
                door3(),
                light3_1(),
                light3_2(),
                light3_3()
        ));
    }

    @Bean
    public Room hallRoom() {
        return new Room("hallroom", "r0", Arrays.asList(
                door3(),
                light3_1(),
                light3_2(),
                light3_3()
        ));
    }

    @Bean public Door door1() {
        return new Door("3", false);
    }

    @Bean public Door door2() {
        return new Door("5", false);
    }

    @Bean public Door door3() {
        return new Door("9", false);
    }

    @Bean public Door hallDoor() {
        return new Door("13", false);
    }

    @Bean public Light light1_1() {
        return new Light("1", false);
    }

    @Bean public Light light1_2() {
        return new Light("2", false);
    }

    @Bean public Light light2_1() {
        return new Light("4", false);
    }

    @Bean public Light light3_1() {
        return new Light("6", false);
    }

    @Bean public Light light3_2() {
        return new Light("7", false);
    }

    @Bean public Light light3_3() {
        return new Light("8", false);
    }

    @Bean public Light hallLight_1() {
        return new Light("10", false);
    }

    @Bean public Light hallLight_2() {
        return new Light("11", false);
    }

    @Bean public Light hallLight_3() {
        return new Light("12", false);
    }


    @Bean
    public SmartHomeIO smartHomeIO() {
        return new SmartHomeJsonIO();
    }
}

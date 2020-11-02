package ru.sbt.mipt.smarthome;


import ru.sbt.mipt.smarthome.components.Door;
import ru.sbt.mipt.smarthome.components.Light;
import ru.sbt.mipt.smarthome.components.Room;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.components.alarm.Alarm;
import java.util.Arrays;


public class HomeBuilder {
    public static SmartHome buildSampleHome(String alarmId) {
        return new SmartHome("super_sanya_smarthome", Arrays.asList(
                new Room("kitchen", "kitchen_0", Arrays.asList(
                        new Light("1", false),
                        new Light("2", true),
                        new Door("3", false)
                )),
                new Room("bathroom", "bathroom0234", Arrays.asList(
                        new Light("4", true),
                        new Door("5", false)
                )),
                new Room("bedroom", "bedroom12", Arrays.asList(
                        new Light("6", false),
                        new Light("7", false),
                        new Light("8", false),
                        new Door("9", false)
                )),
                new Room("hall", "hall007", Arrays.asList(
                        new Light("10", false),
                        new Light("11", false),
                        new Light("12", false),
                        new Door("13", false)
                )),
                new Alarm(alarmId)
        ));
    }
}

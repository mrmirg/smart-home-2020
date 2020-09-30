package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.Device;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.events.EventGenerator;

import java.util.ArrayList;

public class EventGeneratorBuilder {

    public static EventGenerator buildRandomGenerator(SmartHome smartHome, int iterCount) {
        ArrayList<SensorEvent> events = new ArrayList<>();

        for (Room room : smartHome.getRooms()) {
            for (Device device : room.getDevices()) {
                if (device instanceof Door) {
                    events.add(new DoorOpenedEvent((Door)device));
                    events.add(new DoorClosedEvent((Door)device));
                }
                if (device instanceof Light) {
                    events.add(new LightOnEvent((Light)device));
                    events.add(new LightOffEvent((Light)device));
                }
            }
        }

        return new RandomEventGenerator(events, iterCount);
    }
}

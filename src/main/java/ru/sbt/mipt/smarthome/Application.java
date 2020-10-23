package ru.sbt.mipt.smarthome;


import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.RandomEventGeneratorBuilderWithIntIds;
import ru.sbt.mipt.smarthome.handlers.*;

import java.io.IOException;
import java.util.Arrays;


public class Application {
    public static void main(String... args) throws IOException {
        SmartHomeIO smartHomeIO = new SmartHomeJsonIO();
        SmartHome smartHome = smartHomeIO.readHome("src/resources/smarthome.json");

        String alarmId = "alarm";
        if (smartHome == null) {
            smartHome = HomeBuilder.buildSampleHome(alarmId);
        }

        var eventGenerator = new RandomEventGeneratorBuilderWithIntIds(0, 13)
                .buildRandomGenerator(100);

        var sensorEventHandler = new AlarmDecorator(
                smartHome,
                alarmId,
                new CompositeEventHandler(Arrays.asList(
                        new DoorHandler(smartHome),
                        new LightHandler(smartHome),
                        new AlarmHandler(smartHome)
                ))
        );

        EventLoop eventLoop = new EventLoop(eventGenerator, sensorEventHandler);
        eventLoop.spin();
    }
}

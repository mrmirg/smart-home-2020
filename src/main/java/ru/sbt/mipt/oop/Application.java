package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.EventGenerator;
import ru.sbt.mipt.oop.events.RandomEventGeneratorBuilderWithIntIds;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.handlers.SensorEventHandler;

import java.io.IOException;


public class Application {
    public static void main(String... args) {
        SmartHomeIO smartHomeIO = new SmartHomeJsonIO();

        try {
            smartHomeIO.readHome("src/resources/smarthome.json");
        } catch (IOException e) {
            System.out.println("Failed to deserialize smartHome\n" + e.getMessage());
        }


        SmartHome smartHome = HomeBuilder.buildSampleHome();

        EventGenerator eventGenerator = new RandomEventGeneratorBuilderWithIntIds(0, 13)
                .buildRandomGenerator(100);
        HandlerManager handlerManager = new HandlerManagerBuilder(smartHome)
                .buildDefaultManager();

        EventLoop eventLoop = new EventLoop(eventGenerator, handlerManager);
        eventLoop.spin();
    }
}

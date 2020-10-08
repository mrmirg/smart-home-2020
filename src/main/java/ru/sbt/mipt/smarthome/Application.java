package ru.sbt.mipt.smarthome;

import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.EventGenerator;
import ru.sbt.mipt.smarthome.events.RandomEventGeneratorBuilderWithIntIds;

import java.io.IOException;


public class Application {
    public static void main(String... args) {
        SmartHomeIO smartHomeIO = new SmartHomeJsonIO();
        SmartHome smartHome;

        try {
            smartHome = smartHomeIO.readHome("src/resources/smarthome.json");
        } catch (IOException e) {
            System.out.println("Failed to deserialize smartHome\nNo idea what to do with this\n" + e.getMessage());
            smartHome = HomeBuilder.buildSampleHome();
        }

        EventGenerator eventGenerator = new RandomEventGeneratorBuilderWithIntIds(0, 13)
                .buildRandomGenerator(100);
        HandlerManager handlerManager = new HandlerManagerBuilder(smartHome)
                .buildDefaultManager();

        EventLoop eventLoop = new EventLoop(eventGenerator, handlerManager);
        eventLoop.spin();
    }
}

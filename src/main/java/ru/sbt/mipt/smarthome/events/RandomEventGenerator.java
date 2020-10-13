package ru.sbt.mipt.smarthome.events;

import java.util.ArrayList;
import java.util.Random;

public class RandomEventGenerator implements EventGenerator {
    private final ArrayList<SensorEvent> events;
    private final Random randomGenerator;
    private final int iterCount;
    private int curIterCount;


    public RandomEventGenerator(ArrayList<SensorEvent> events, int iterCount) {
        this.events = events;
        this.iterCount = iterCount;
        this.curIterCount = 0;
        randomGenerator = new Random();
    }


    @Override
    public SensorEvent nextEvent() {
        if (iterCount == curIterCount) {
            return null;
        }
        curIterCount++;
        return events.get(Math.abs(randomGenerator.nextInt()) % events.size());
    }
}

package ru.sbt.mipt.oop.events;


import java.util.ArrayList;


public class RandomEventGeneratorBuilderWithIntIds {
    private final int idLowerBound;
    private final int idUpperBound;

    public RandomEventGeneratorBuilderWithIntIds(int idLowerBound, int idUpperBound) {
        this.idLowerBound = idLowerBound;
        this.idUpperBound = idUpperBound;
    }

    public EventGenerator buildRandomGenerator(int iterCount) {
        ArrayList<SensorEvent> events = new ArrayList<>();

        for (int i = idLowerBound; i < idUpperBound; i++) {
            events.add(new DoorOpenedEvent(Integer.toString(i)));
            events.add(new DoorClosedEvent(Integer.toString(i)));
            events.add(new LightOnEvent(Integer.toString(i)));
            events.add(new LightOffEvent(Integer.toString(i)));
        }

        return new RandomEventGenerator(events, iterCount);
    }
}

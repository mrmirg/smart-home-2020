package ru.sbt.mipt.smarthome.events;


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
            events.add(new DoorOpened(Integer.toString(i), true));
            events.add(new DoorOpened(Integer.toString(i), false));
            events.add(new LightOn(Integer.toString(i), true));
            events.add(new LightOn(Integer.toString(i), false));
        }
        return new RandomEventGenerator(events, iterCount);
    }
}

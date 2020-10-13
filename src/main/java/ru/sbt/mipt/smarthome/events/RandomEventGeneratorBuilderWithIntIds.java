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
            events.add(new DoorOpened(Integer.toString(i)));
            events.add(new DoorClosed(Integer.toString(i)));
            events.add(new LightOn(Integer.toString(i)));
            events.add(new LightOff(Integer.toString(i)));
        }
        return new RandomEventGenerator(events, iterCount);
    }
}

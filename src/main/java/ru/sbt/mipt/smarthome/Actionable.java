package ru.sbt.mipt.smarthome;


import ru.sbt.mipt.smarthome.actions.Action;


public interface Actionable {
    boolean applyAction(Action e);
}

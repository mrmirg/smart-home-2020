package ru.sbt.mipt.smarthome.test;


import org.junit.jupiter.api.Test;
import ru.sbt.mipt.smarthome.components.*;
import ru.sbt.mipt.smarthome.events.DoorOpened;
import ru.sbt.mipt.smarthome.handlers.DoorHandler;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DoorHandlerTest {
    @Test
    public void openNonValidDoorTest() {
        //given
        Door door = new Door("2", false);
        Light light = new Light("1", false);

        HomeComponent atticRoom = new Room("attic", "attic", Arrays.asList(door, light));
        SmartHome smartHome = new SmartHome("strangehome", Collections.singletonList(atticRoom));
        DoorHandler doorHandler = new DoorHandler(smartHome);
        DoorOpened nonExistingDoorOpened = new DoorOpened("3");
        DoorOpened nonADoorOpened = new DoorOpened("1");

        // when
        boolean success = doorHandler.processEvent(nonADoorOpened) ||
                doorHandler.processEvent(nonExistingDoorOpened);

        // then
        assertFalse(success);
        assertFalse(door.isOpened());
        assertFalse(light.isOn());
    }

    @Test
    public void openCloseExistingDoorTest() {
        //given
        Door door = new Door("2", false);
        Light light = new Light("1", false);

        HomeComponent atticRoom = new Room("attic", "attic", Arrays.asList(door, light));
        SmartHome smartHome = new SmartHome("strangehome", Collections.singletonList(atticRoom));
        DoorHandler doorHandler = new DoorHandler(smartHome);
        DoorOpened doorOpened = new DoorOpened("2");

        // when
        boolean success = doorHandler.processEvent(doorOpened);

        // then
        assertTrue(success);
        assertTrue(door.isOpened());
        assertFalse(light.isOn());
    }
}

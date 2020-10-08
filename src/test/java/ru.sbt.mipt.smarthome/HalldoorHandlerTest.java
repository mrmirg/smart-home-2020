package ru.sbt.mipt.smarthome.test;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.smarthome.components.Door;
import ru.sbt.mipt.smarthome.components.Light;
import ru.sbt.mipt.smarthome.components.Room;
import ru.sbt.mipt.smarthome.components.SmartHome;
import ru.sbt.mipt.smarthome.events.DoorClosed;
import ru.sbt.mipt.smarthome.events.DoorOpened;
import ru.sbt.mipt.smarthome.handlers.HalldoorHandler;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class HalldoorHandlerTest {
    @Test
    public void openCloseValidHalldoor() {
        // given
        Door halldoor = new Door("d1", false);
        Light light1 = new Light("l1", false);
        Light light2 = new Light("l2", false);

        Door anotherDoor = new Door("d2", false);
        Light anotherLight = new Light("l3", false);

        Room room = new Room("hall", "h", Arrays.asList(halldoor, light1, light2));
        Room anotherRoom = new Room("bedroom", "b", Arrays.asList(anotherDoor, anotherLight));

        SmartHome smartHome  = new SmartHome("supersmart", Arrays.asList(room, anotherRoom));
        HalldoorHandler halldoorHandler = new HalldoorHandler(smartHome, halldoor.getId());

        // when
        boolean successOpen = halldoorHandler.processEvent(new DoorOpened(halldoor.getId()));

        // then
        assertTrue(successOpen);
        assertTrue(light1.isOn());
        assertTrue(light2.isOn());
        assertTrue(halldoor.isOpened());
        assertFalse(anotherDoor.isOpened());
        assertFalse(anotherLight.isOn());

        // when
        boolean successClose = halldoorHandler.processEvent(new DoorClosed(halldoor.getId()));

        // then
        assertTrue(successClose);
        assertFalse(light1.isOn());
        assertFalse(light2.isOn());
        assertFalse(halldoor.isOpened());
        assertFalse(anotherDoor.isOpened());
        assertFalse(anotherLight.isOn());
    }

    @Test
    public void openInvalidHalldoor() {
        // given
        Door halldoor = new Door("d1", false);
        Light light1 = new Light("l1", false);
        Light light2 = new Light("l2", false);

        Door anotherDoor = new Door("d2", false);
        Light anotherLight = new Light("l3", false);

        Room room = new Room("hall", "h", Arrays.asList(halldoor, light1, light2));
        Room anotherRoom = new Room("bedroom", "b", Arrays.asList(anotherDoor, anotherLight));

        SmartHome smartHome  = new SmartHome("supersmart", Arrays.asList(room, anotherRoom));
        HalldoorHandler halldoorHandler = new HalldoorHandler(smartHome, halldoor.getId());

        // when
        boolean successOpen = halldoorHandler.processEvent(new DoorOpened("bla"));

        // then
        assertFalse(successOpen);
        assertFalse(light1.isOn());
        assertFalse(light2.isOn());
        assertFalse(halldoor.isOpened());
        assertFalse(anotherDoor.isOpened());
        assertFalse(anotherLight.isOn());
    }
}

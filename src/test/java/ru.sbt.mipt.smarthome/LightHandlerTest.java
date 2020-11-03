package ru.sbt.mipt.smarthome.test;


import org.junit.jupiter.api.Test;
import ru.sbt.mipt.smarthome.components.*;
import ru.sbt.mipt.smarthome.events.LightOn;
import ru.sbt.mipt.smarthome.handlers.LightHandler;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LightHandlerTest {
    @Test
    public void turnOnNonValidLight() {
        //given
        Door door = new Door("2", false);
        Light light = new Light("1", false);

        HomeComponent atticRoom = new Room("attic", "attic", Arrays.asList(door, light));
        SmartHome smartHome = new SmartHome("strangehome", Collections.singletonList(atticRoom));
        LightHandler lightHandler = new LightHandler(smartHome);
        LightOn nonExistingLightOn = new LightOn("3");
        LightOn nonALightOn = new LightOn("2");

        // when
        boolean success = lightHandler.processEvent(nonALightOn) ||
                lightHandler.processEvent(nonExistingLightOn);

        // then
        assertFalse(success);
        assertFalse(light.isOn());
        assertFalse(door.isOpened());
    }


    @Test
    public void turnOnOffExistingLight() {
        //given
        Door door = new Door("2", false);
        Light light = new Light("1", false);

        HomeComponent atticRoom = new Room("attic", "attic", Arrays.asList(door, light));
        SmartHome smartHome = new SmartHome("strangehome", Collections.singletonList(atticRoom));
        LightHandler lightHandler = new LightHandler(smartHome);
        LightOn lightOn = new LightOn("1");

        // when
        boolean success = lightHandler.processEvent(lightOn);

        // then
        assertTrue(success);
        assertTrue(light.isOn());
        assertFalse(door.isOpened());
    }
}

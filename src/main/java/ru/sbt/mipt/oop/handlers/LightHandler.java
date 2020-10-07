package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.Device;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.events.*;

public class LightHandler implements SensorEventHandler {
    SmartHome smartHome;

    public LightHandler(SmartHome home) {
        this.smartHome = home;
    }

    public boolean processEvent(SensorEvent event) {
        if (event instanceof LightOnEvent || event instanceof LightOffEvent) {
            Device device = smartHome.getDeviceById(event.getDeviceId());
            if (device instanceof Light) {
                Light lightDevice = (Light) device;
                boolean lightOn = event instanceof LightOnEvent;
                lightDevice.setOn(lightOn);

                System.out.println(
                        "Light set to " + device.getId() + (lightOn ? " ON " : " OFF ") + "successfully"
                );
                return true;
            } else {
                System.out.println("Failed to process : device " + (device == null ? "null" : device.getId()) + " is not a Light");
                return false;
            }
        }
        return false;
    }
}

package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.devices.Device;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.events.DoorClosedEvent;
import ru.sbt.mipt.oop.events.DoorOpenedEvent;
import ru.sbt.mipt.oop.events.SensorEvent;

public class DoorHandler implements SensorEventHandler {
    SmartHome smartHome;

    public DoorHandler(SmartHome home) {
        this.smartHome = home;
    }

    public boolean processEvent(SensorEvent event) {
        if (event instanceof DoorOpenedEvent || event instanceof DoorClosedEvent) {
            Device device = smartHome.getDeviceById(event.getDeviceId());
            if (device instanceof Door) {
                Door doorDevice = (Door) device;
                boolean opened = event instanceof DoorOpenedEvent;
                doorDevice.setOpen(opened);

                System.out.println(
                        "Door " + device.getId() + (opened ? " opened " : " closed ") + "successfully"
                );
                return true;
            } else {
                System.out.println("Failed to process : device " + (device == null ? "null" : device.getId()) + " is not a Door");
                return false;
            }
        }
        return false;
    }
}

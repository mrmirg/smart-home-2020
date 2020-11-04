package rc;


import ru.sbt.mipt.smarthome.commands.SensorCommand;
import java.util.Map;


public class RemoteControlImpl implements RemoteControl {
    private final Map<String, Map<String, SensorCommand>> controlMapping;


    public RemoteControlImpl(Map<String, Map<String, SensorCommand>> controlMapping) {
        this.controlMapping = controlMapping;
    }


    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        try {
            controlMapping.get(rcId).get(buttonCode).execute();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

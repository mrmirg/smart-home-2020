package rc;


import ru.sbt.mipt.smarthome.commands.SensorCommand;

import java.util.Map;


public class RemoteControlImpl implements RemoteControl {
    private final Map<String, SensorCommand> controlMapping;
    private final String rcId;



    public RemoteControlImpl(String rcId, Map<String, SensorCommand> controlMapping) {
        this.controlMapping = controlMapping;
        this.rcId = rcId;
    }


    public String getId() {
        return rcId;
    }


    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        try {
            if (this.rcId.equals(rcId)) {
                controlMapping.get(buttonCode).execute();
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

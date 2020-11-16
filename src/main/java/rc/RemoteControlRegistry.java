package rc;


import java.util.Collection;


public class RemoteControlRegistry {
    /**
     * Register remote control with id rcId.
     * When button on a real remote control device is pressed this library will call remoteControl.onButtonPressed(...).
     * @param remoteControl
     * @param rcId
     */
    private final Collection<RemoteControl> remoteControls;

    public RemoteControlRegistry(Collection<RemoteControl> rc) {
        this.remoteControls = rc;
    }

    public void register(RemoteControl remoteControl) {
        remoteControls.add(remoteControl);
    }
}

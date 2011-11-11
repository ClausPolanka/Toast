package toast.internal.client.emergency;

import static java.lang.String.format;
import toast.dev.airbag.Airbag;
import toast.dev.airbag.AirbagListener;
import toast.dev.gps.Gps;

public class EmergencyMonitor implements AirbagListener {
    private Airbag airbag;
    private Gps gps;

    public EmergencyMonitor(Airbag airbag, Gps gps) {
        this.airbag = airbag;
        this.gps = gps;
    }

    @Override
    public void deployed() {
        System.out.println(format("Emergency occured at lat=%d lon=%d heading=%d speed=%d",
                gps.getLatitude(), gps.getLongitude(), gps.getHeading(), gps.getSpeed()));
    }

    public void shutdown() {
        airbag.removeListener(this);
    }

    public void startup() {
        airbag.addListener(this);
    }
}

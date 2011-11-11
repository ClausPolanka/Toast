package toast.client.emergency;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import toast.dev.airbag.Airbag;
import toast.dev.gps.Gps;

public class Activator implements BundleActivator {
    private EmergencyMonitor em;
    private ServiceReference<?> gpsRef;
    private ServiceReference<?> airbagRef;
    private Gps gps;
    private Airbag airbag;
    private EmergencyMonitor monitor;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Launching");
        gpsRef = context.getServiceReference(Gps.class.getName());
        airbagRef = context.getServiceReference(Airbag.class.getName());
        if (gpsRef == null || airbagRef == null) {
            System.err.println("Unable to auqire GPS or airbag");
            return;
        }
        gps = (Gps) context.getService(gpsRef);
        airbag = (Airbag) context.getService(airbagRef);
        if (gps == null || airbag == null) {
            System.err.println("Unable to auqire GPS or airbag");
            return;
        }
        monitor = new EmergencyMonitor(airbag, gps);
        monitor.startup();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        monitor.shutdown();
        if (gpsRef != null) {
            context.ungetService(gpsRef);
        }
        if (airbag != null) {
            context.ungetService(airbagRef);
        }
        System.out.println("Terminating");
    }

}

package toast.client.emergency;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import toast.dev.airbag.Airbag;
import toast.dev.gps.Gps;

public class Activator implements BundleActivator {
    private EmergencyMonitor em;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Launching");
        Airbag airbag = new Airbag();
        em = new EmergencyMonitor(airbag, new Gps());
        em.startup();
        airbag.deploy();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        em.shutdown();
        System.out.println("Terminating");
    }

}

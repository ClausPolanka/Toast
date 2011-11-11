package toast.internal.dev.airbag.fake.bundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import toast.dev.airbag.Airbag;

public class Activator implements BundleActivator {
    private ServiceRegistration<?> registration;
    private AirbagFake airbag;

    @Override
    public void start(BundleContext context) throws Exception {
        airbag = new AirbagFake();
        airbag.startup();
        registration = context.registerService(Airbag.class.getName(), airbag, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        registration.unregister();
        airbag.shutdown();
    }

}

package toast.internal.dev.airbag.fake.bundle;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import toast.dev.airbag.Airbag;
import toast.dev.airbag.AirbagListener;

public class AirbagFake implements Airbag {
    private List<AirbagListener> listeners = new ArrayList<AirbagListener>();
    private boolean isRunning;
    private Job job;

    @Override
    public void addListener(AirbagListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(AirbagListener listener) {
        listeners.remove(listener);
    }

    public synchronized void startup() {
        isRunning = true;
        job = new Job("Fake Airbag") {

            @Override
            protected IStatus run(IProgressMonitor arg0) {
                deploy();
                if (isRunning) {
                    schedule(5000);
                }
                return Status.OK_STATUS;
            }
        };
    }

    public void deploy() {
        for (AirbagListener listener : listeners) {
            listener.deployed();
        }
    }

    public synchronized void shutdown() {
        isRunning = false;
        job.cancel();
        try {
            job.join();
        } catch (InterruptedException e) {
            // shutting down, safe to ignore
        }
    }
}

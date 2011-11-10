package toast.dev.airbag;

import java.util.ArrayList;
import java.util.List;

public class Airbag {
	private List<AirbagListener> listeners = new ArrayList<AirbagListener>();

	public void addListener(AirbagListener listener) {
		listeners.add(listener);
	}

	public void deploy() {
		for (AirbagListener listener : listeners) {
			listener.deployed();
		}
	}

	public void removeListener(AirbagListener listener) {
		listeners.remove(listener);
	}
}

package de.shazzarr.loratien.server.component;

import com.badlogic.ashley.core.Component;

public class SpiritualRaptureComponent implements Component {

	private int spiritualRapture = 0;

	public SpiritualRaptureComponent() {
	}

	public SpiritualRaptureComponent(int spiritualRapture) {
		this.spiritualRapture = spiritualRapture;
	}

	public int getSpiritualRapture() {
		return spiritualRapture;
	}

	public void setSpiritualRapture(int spiritualRapture) {
		this.spiritualRapture = spiritualRapture;
	}

	@Override
	public String toString() {
		return "Spiritual raptore: " + spiritualRapture;
	}
}

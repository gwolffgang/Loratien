package de.shazzarr.loratien.server.component.attribute;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class PresenceComponent implements Component {

	private int presence;

	public PresenceComponent() {
		this(/* Default: */ 1);
	}

	public PresenceComponent(int presence) {
		this.presence = presence;
	}

	public int getPresence() {
		return presence;
	}

	public void setPresence(int presence) {
		this.presence = presence;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("presence") + ": " + this.presence;
	}
}

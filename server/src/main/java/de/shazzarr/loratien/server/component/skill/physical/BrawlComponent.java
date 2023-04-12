package de.shazzarr.loratien.server.component.skill.physical;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class BrawlComponent implements Component {

	private int brawl;

	public BrawlComponent() {
		this(/* Default: */ 0);
	}

	public BrawlComponent(int brawl) {
		this.brawl = brawl;
	}

	public int getBrawl() {
		return brawl;
	}

	public void setBrawl(int brawl) {
		this.brawl = brawl;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("brawl") + ": " + this.brawl;
	}
}

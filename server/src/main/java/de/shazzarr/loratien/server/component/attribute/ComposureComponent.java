package de.shazzarr.loratien.server.component.attribute;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class ComposureComponent implements Component {

	private int composure;

	public ComposureComponent() {
		this(/* Default: */ 1);
	}

	public ComposureComponent(int composure) {
		this.composure = composure;
	}

	public int getComposure() {
		return composure;
	}

	public void setComposure(int composure) {
		this.composure = composure;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("composure") + ": " + this.composure;
	}
}

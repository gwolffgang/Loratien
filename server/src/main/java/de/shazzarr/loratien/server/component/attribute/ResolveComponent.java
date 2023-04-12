package de.shazzarr.loratien.server.component.attribute;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class ResolveComponent implements Component {

	private int resolve;

	public ResolveComponent() {
		this(/* Default: */ 1);
	}

	public ResolveComponent(int resolve) {
		this.resolve = resolve;
	}

	public int getResolve() {
		return resolve;
	}

	public void setResolve(int resolve) {
		this.resolve = resolve;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("resolve") + ": " + this.resolve;
	}
}

package de.shazzarr.loratien.server.component;

import com.badlogic.ashley.core.Component;

public class MagicaComponent implements Component {

	private int magica = 1;

	public MagicaComponent() {
	}

	public MagicaComponent(int magica) {
		this.magica = magica;
	}

	public int getMagica() {
		return magica;
	}

	public void setMagica(int magica) {
		this.magica = magica;
	}

	@Override
	public String toString() {
		return "Magica: " + magica;
	}
}

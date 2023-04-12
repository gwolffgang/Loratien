package de.shazzarr.loratien.server.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;

import de.shazzarr.loratien.server.Loratien;
import de.shazzarr.loratien.server.component.AquaticComponent;
import de.shazzarr.loratien.server.component.HealthComponent;
import de.shazzarr.loratien.server.component.MagicaComponent;
import de.shazzarr.loratien.server.component.MovementComponent;
import de.shazzarr.loratien.server.component.NameComponent;
import de.shazzarr.loratien.server.component.OwnageComponent;
import de.shazzarr.loratien.server.component.SpiritualRaptureComponent;
import de.shazzarr.loratien.server.entity.tileobject.Lifeform;
import de.shazzarr.loratien.server.enumeration.Components;
import de.shazzarr.loratien.server.helper.Mappers;

abstract public class GameObject {

	private final Entity entity = new Entity();

	public GameObject() {
		Loratien.engine.addEntity(entity);
	}

	public void addComponent(Components component) {
		switch (component) {
		case aquatic:
			entity.add(new AquaticComponent());
			break;
		case health:
			entity.add(new HealthComponent());
			break;
		case magica:
			entity.add(new MagicaComponent());
			break;
		case movement:
			entity.add(new MovementComponent());
			break;
		case name:
			entity.add(new NameComponent());
			break;
		case ownage:
			entity.add(new OwnageComponent());
			break;
		case spiritualRapture:
			entity.add(new SpiritualRaptureComponent());
			break;
		default:
		}
	}

	public void addComponent(Components component, Lifeform value) {
		switch (component) {
		case ownage:
			entity.add(new OwnageComponent(value));
		default:
		}
	}

	public void addComponent(Components component, boolean value) {
		switch (component) {
		case aquatic:
			entity.add(new AquaticComponent(true));
			break;
		default:
		}
	}

	public void addComponent(Components component, int value) {
		switch (component) {
		case health:
			entity.add(new HealthComponent(value));
			break;
		case magica:
			entity.add(new MagicaComponent(value));
			break;
		case movement:
			entity.add(new MovementComponent(value));
			break;
		case spiritualRapture:
			entity.add(new SpiritualRaptureComponent(value));
			break;
		default:
		}
	}

	public void addComponent(Components component, String value) {
		switch (component) {
		case name:
			entity.add(new NameComponent(value));
			break;
		default:
		}
	}

	public void removeComponent(Components component) {
		switch (component) {
		case all:
			entity.removeAll();
			break;
		case aquatic:
			entity.remove(AquaticComponent.class);
			break;
		case health:
			entity.remove(HealthComponent.class);
			break;
		case magica:
			entity.remove(MagicaComponent.class);
			break;
		case movement:
			entity.remove(MovementComponent.class);
			break;
		case name:
			entity.remove(NameComponent.class);
			break;
		case ownage:
			entity.remove(OwnageComponent.class);
			break;
		case spiritualRapture:
			entity.remove(SpiritualRaptureComponent.class);
			break;
		default:
		}
	}

	public void removeAllComponents() {
		entity.removeAll();
	}

	public boolean isAquatic() {
		final AquaticComponent ac = Mappers.aquatic.get(entity);
		if (ac == null) {
			return false;
		}
		return ac.isAquatic();
	}

	public void setAquatic(boolean aquatic) {
		final AquaticComponent ac = Mappers.aquatic.get(entity);
		if (ac == null) {
			return;
		}
		ac.setAquatic(aquatic);
	}

	public int getHealth() {
		final HealthComponent hc = Mappers.health.get(entity);
		if (hc == null) {
			return 0;
		}
		return hc.getHealth();
	}

	public void setHealth(int health) {
		final HealthComponent hc = Mappers.health.get(entity);
		if (hc == null) {
			return;
		}
		hc.setHealth(health);
	}

	public int getMagica() {
		final MagicaComponent mc = Mappers.magica.get(entity);
		if (mc == null) {
			return 0;
		}
		return mc.getMagica();
	}

	public void setMagica(int magica) {
		final MagicaComponent mc = Mappers.magica.get(entity);
		if (mc == null) {
			return;
		}
		mc.setMagica(magica);
	}

	public int getMovement() {
		final MovementComponent mc = Mappers.movement.get(entity);
		if (mc == null) {
			return 0;
		}
		return mc.getMovement();
	}

	public void setMovement(int movement) {
		final MovementComponent mc = Mappers.movement.get(entity);
		if (mc == null) {
			return;
		}
		mc.setMovement(movement);
	}

	public String getName() {
		final NameComponent nc = Mappers.name.get(entity);
		if (nc == null) {
			return null;
		}
		return nc.getName();
	}

	public void setName(String name) {
		final NameComponent nc = Mappers.name.get(entity);
		if (nc == null) {
			return;
		}
		nc.setName(name);
	}

	public Lifeform getOwner() {
		final OwnageComponent oc = Mappers.ownage.get(entity);
		if (oc == null) {
			return null;
		}
		return oc.getOwner();
	}

	public void setOwner(Lifeform owner) {
		final OwnageComponent oc = Mappers.ownage.get(entity);
		if (oc == null) {
			return;
		}
		oc.setOwner(owner);
	}

	public int getSpiritualRapture() {
		final SpiritualRaptureComponent src = Mappers.spiritualRapture.get(entity);
		if (src == null) {
			return 0;
		}
		return src.getSpiritualRapture();
	}

	public void setSpiritualRapture(int spiritualRapture) {
		final SpiritualRaptureComponent src = Mappers.spiritualRapture.get(entity);
		if (src == null) {
			return;
		}
		src.setSpiritualRapture(spiritualRapture);
	}

	public void printStats() {
		System.out.println();
		System.out.print("|");
		for (int i = 0; i < 50 + 2; i++) {
			System.out.print("~");
		}
		System.out.println("|");
		System.out.printf("| %-50s |%n", this.getClass().getSimpleName());
		System.out.print("|");
		for (int i = 0; i < 50 + 2; i++) {
			System.out.print("~");
		}
		System.out.println("|");
		final ImmutableArray<Component> components = entity.getComponents();
		if (components.size() != 0) {
			for (final Component component : components) {
				System.out.printf("| %-50s |%n", component);
			}
		} else {
			System.out.printf("| %-50s |%n", "no components listed");
		}
		System.out.print("|");
		for (int i = 0; i < 50 + 2; i++) {
			System.out.print("~");
		}
		System.out.println("|");
	}

}

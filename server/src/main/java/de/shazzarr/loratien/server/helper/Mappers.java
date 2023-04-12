package de.shazzarr.loratien.server.helper;

import com.badlogic.ashley.core.ComponentMapper;

import de.shazzarr.loratien.server.component.AquaticComponent;
import de.shazzarr.loratien.server.component.HealthComponent;
import de.shazzarr.loratien.server.component.MagicaComponent;
import de.shazzarr.loratien.server.component.MovementComponent;
import de.shazzarr.loratien.server.component.NameComponent;
import de.shazzarr.loratien.server.component.OwnageComponent;
import de.shazzarr.loratien.server.component.SpiritualRaptureComponent;

public abstract class Mappers {
	public static final ComponentMapper<AquaticComponent> aquatic = ComponentMapper.getFor(AquaticComponent.class);
	public static final ComponentMapper<HealthComponent> health = ComponentMapper.getFor(HealthComponent.class);
	public static final ComponentMapper<MagicaComponent> magica = ComponentMapper.getFor(MagicaComponent.class);
	public static final ComponentMapper<MovementComponent> movement = ComponentMapper.getFor(MovementComponent.class);
	public static final ComponentMapper<NameComponent> name = ComponentMapper.getFor(NameComponent.class);
	public static final ComponentMapper<OwnageComponent> ownage = ComponentMapper.getFor(OwnageComponent.class);
	public static final ComponentMapper<SpiritualRaptureComponent> spiritualRapture = ComponentMapper
			.getFor(SpiritualRaptureComponent.class);
}

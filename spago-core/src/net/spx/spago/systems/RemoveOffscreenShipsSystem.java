package net.spx.spago.systems;

import net.spx.spago.components.Bounds;
import net.spx.spago.components.Health;
import net.spx.spago.components.Player;
import net.spx.spago.components.Position;
import net.spx.spago.components.Velocity;
import net.spx.spago.utils.ScreenConfiguration;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;

public class RemoveOffscreenShipsSystem extends IntervalEntityProcessingSystem {
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Bounds> bm;

	@SuppressWarnings("unchecked")
	public RemoveOffscreenShipsSystem() {
		super(Aspect.getAspectForAll(Velocity.class, Position.class, Health.class, Bounds.class).exclude(Player.class), 5);
	}

	@Override
	protected void process(Entity e) {
		Position position = pm.get(e);
		Bounds bounds = bm.get(e);
		
		if(position.y < -ScreenConfiguration.getInstance().getFrameHeight()/2-bounds.radius) {
			e.deleteFromWorld();
		}
	}

}

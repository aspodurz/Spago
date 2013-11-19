package net.spx.spago.systems;

import net.spx.spago.components.ParallaxStar;
import net.spx.spago.components.Position;
import net.spx.spago.utils.ScreenConfiguration;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;

public class ParallaxStarRepeatingSystem extends IntervalEntityProcessingSystem {
	@Mapper
	ComponentMapper<Position> pm;

	public ParallaxStarRepeatingSystem() {
		super(Aspect.getAspectFor(ParallaxStar.class, Position.class), 1);
	}

	@Override
	protected void process(Entity e) {
		Position position = pm.get(e);

		if (position.y < -ScreenConfiguration.getInstance().getFrameHeight() / 2) {
			position.y = ScreenConfiguration.getInstance().getFrameHeight() / 2;
		}
	}

}

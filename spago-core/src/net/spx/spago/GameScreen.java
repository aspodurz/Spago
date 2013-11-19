package net.spx.spago;

import net.spx.spago.systems.CollisionSystem;
import net.spx.spago.systems.ColorAnimationSystem;
import net.spx.spago.systems.EntitySpawningTimerSystem;
import net.spx.spago.systems.ExpiringSystem;
import net.spx.spago.systems.HealthRenderSystem;
import net.spx.spago.systems.HudRenderSystem;
import net.spx.spago.systems.MovementSystem;
import net.spx.spago.systems.ParallaxStarRepeatingSystem;
import net.spx.spago.systems.PlayerInputSystem;
import net.spx.spago.systems.RemoveOffscreenShipsSystem;
import net.spx.spago.systems.ScaleAnimationSystem;
import net.spx.spago.systems.SpriteRenderSystem;
import net.spx.spago.tools.EntityFactory;
import net.spx.spago.utils.ScreenConfiguration;

import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen implements Screen {
	private Game game;
	private World world;
	private OrthographicCamera camera;

	private SpriteRenderSystem spriteRenderSystem;
	private HealthRenderSystem healthRenderSystem;
	private HudRenderSystem hudRenderSystem;

	public GameScreen(Game game) {
		this.game = game;
		this.camera = new OrthographicCamera(ScreenConfiguration.getInstance().getFrameWidth(),
				ScreenConfiguration.getInstance().getFrameHeight());

		world = new World();

		world.setManager(new GroupManager());

		world.setSystem(new MovementSystem());
		world.setSystem(new PlayerInputSystem(camera));
		world.setSystem(new CollisionSystem());
		world.setSystem(new ExpiringSystem());
		world.setSystem(new EntitySpawningTimerSystem());
		world.setSystem(new ParallaxStarRepeatingSystem());
		world.setSystem(new ColorAnimationSystem());
		world.setSystem(new ScaleAnimationSystem());
		world.setSystem(new RemoveOffscreenShipsSystem());

		spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera),
				true);
		healthRenderSystem = world.setSystem(new HealthRenderSystem(camera),
				true);
		hudRenderSystem = world.setSystem(new HudRenderSystem(camera), true);

		world.initialize();

		EntityFactory.createPlayer(world, 0, 0).addToWorld();

		for (int i = 0; 500 > i; i++) {
			EntityFactory.createStar(world).addToWorld();
		}

	}

	@Override
	public void render(float delta) {
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();

		world.setDelta(delta);
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			for (int i = 0; 10 > i; i++) {
				world.process();
			}
		}
		world.process();

		spriteRenderSystem.process();
		healthRenderSystem.process();
		hudRenderSystem.process();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}

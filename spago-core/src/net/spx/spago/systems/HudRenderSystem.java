package net.spx.spago.systems;

import java.util.HashMap;

import net.spx.spago.components.Position;
import net.spx.spago.components.Sprite;
import net.spx.spago.utils.ScreenConfiguration;

import com.artemis.ComponentMapper;
import com.artemis.annotations.Mapper;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HudRenderSystem extends VoidEntitySystem {
	@Mapper
	ComponentMapper<Position> pm;
	@Mapper
	ComponentMapper<Sprite> sm;

	private HashMap<String, AtlasRegion> regions;
	private TextureAtlas textureAtlas;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont font;

	public HudRenderSystem(OrthographicCamera camera) {
		this.camera = camera;
	}

	@Override
	protected void initialize() {
		regions = new HashMap<String, AtlasRegion>();
		textureAtlas = new TextureAtlas(Gdx.files.internal("textures/pack"),
				Gdx.files.internal("textures"));
		for (AtlasRegion r : textureAtlas.getRegions()) {
			regions.put(r.name, r);
		}

		batch = new SpriteBatch();

		Texture fontTexture = new Texture(
				Gdx.files.internal("fonts/normal_0.png"));
		fontTexture.setFilter(TextureFilter.Linear,
				TextureFilter.MipMapLinearLinear);
		TextureRegion fontRegion = new TextureRegion(fontTexture);
		font = new BitmapFont(Gdx.files.internal("fonts/normal.fnt"),
				fontRegion, false);
		font.setUseIntegerPositions(false);
	}

	@Override
	protected void begin() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}

	@Override
	protected void processSystem() {
		batch.setColor(1, 1, 1, 1);
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(),
				-(ScreenConfiguration.getInstance().getFrameWidth() / 2) + 20, ScreenConfiguration.getInstance().getFrameHeight() / 2 - 20);
		font.draw(batch, "Active entities: "
				+ world.getEntityManager().getActiveEntityCount(),
				-(ScreenConfiguration.getInstance().getFrameWidth() / 2) + 20, ScreenConfiguration.getInstance().getFrameHeight() / 2 - 40);
		font.draw(batch, "Total created: "
				+ world.getEntityManager().getTotalCreated(),
				-(ScreenConfiguration.getInstance().getFrameWidth() / 2) + 20, ScreenConfiguration.getInstance().getFrameHeight() / 2 - 60);
		font.draw(batch, "Total deleted: "
				+ world.getEntityManager().getTotalDeleted(),
				-(ScreenConfiguration.getInstance().getFrameWidth() / 2) + 20, ScreenConfiguration.getInstance().getFrameHeight() / 2 - 80);
	}

	@Override
	protected void end() {
		batch.end();
	}

}

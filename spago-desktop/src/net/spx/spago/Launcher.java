package net.spx.spago;

import net.spx.spago.utils.Environment;
import net.spx.spago.utils.ImagePacker;
import net.spx.spago.utils.ScreenConfiguration;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Launcher extends Game {
	public static final int FRAME_WIDTH = 1280;
	public static final int FRAME_HEIGHT = 900;

	private static Environment config = Environment.DESKTOP;

	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}

	public static void main(String[] args) {
		ImagePacker.run(config);

		LwjglApplicationConfiguration lwjglApplicationConfiguration = new LwjglApplicationConfiguration();
		lwjglApplicationConfiguration.fullscreen = false;
		lwjglApplicationConfiguration.width = FRAME_WIDTH;
		lwjglApplicationConfiguration.height = FRAME_HEIGHT;
		lwjglApplicationConfiguration.vSyncEnabled = false;
		lwjglApplicationConfiguration.title = "Spago";
		ScreenConfiguration.initInstance(FRAME_WIDTH, FRAME_HEIGHT);
		new LwjglApplication(new Launcher(), lwjglApplicationConfiguration);
	}
}

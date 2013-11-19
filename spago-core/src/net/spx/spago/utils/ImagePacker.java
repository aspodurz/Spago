package net.spx.spago.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class ImagePacker {

	public static void run(Environment config) {
		Settings settings = new Settings();
		settings.filterMin = Texture.TextureFilter.Linear;
		settings.filterMag = Texture.TextureFilter.Linear;
		settings.pot = false;
		TexturePacker2.process(settings, getRes(config, "textures-original"),
				"dest/textures", "pack");
	}

	private static String getRes(Environment config, String in) {
		String prefix = null;

		if (Environment.DESKTOP.equals(config)) {
			prefix = "../spago-core/";
		}

		return prefix + in;
	}

}

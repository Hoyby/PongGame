package com.hoyby.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hoyby.game.MyPongGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = MyPongGame.WIDTH;
		config.height = MyPongGame.HEIGHT;
		config.title = MyPongGame.TITLE;

		new LwjglApplication(new MyPongGame(), config);
	}
}

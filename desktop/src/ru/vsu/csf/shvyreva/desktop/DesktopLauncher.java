package ru.vsu.csf.shvyreva.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.vsu.csf.shvyreva.ThreeInARow;

public class DesktopLauncher {


    public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Звездочка";
        config.addIcon("assets/icon1.png", Files.FileType.Internal);
        config.width = ThreeInARow.WIDTH;
        config.height = ThreeInARow.HEIGHT;
		new LwjglApplication(new ThreeInARow(), config);
	}
}

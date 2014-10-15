package ru.vsu.csf.shvyreva;

import com.badlogic.gdx.Game;
import ru.vsu.csf.shvyreva.screens.MainMenuScreen;

public class ThreeInARow extends Game {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    @Override
    public void create() {
        this.setScreen(new MainMenuScreen(this));
    }



/*	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}*/

}

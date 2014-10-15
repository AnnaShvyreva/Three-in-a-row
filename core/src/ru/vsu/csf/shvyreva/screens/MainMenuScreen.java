package ru.vsu.csf.shvyreva.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.vsu.csf.shvyreva.ThreeInARow;

public class MainMenuScreen extends ThreeInARowScreen {


    Sprite hello;

    public MainMenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();

        hello = new Sprite(new Texture(Gdx.files.internal("assets/hellowindow.png"))) {{
            setX(0);
            setY(0);
        }};

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = ThreeInARow.HEIGHT - screenY;

                if (hello.getBoundingRectangle().contains(screenX, screenY))
                    game.setScreen(new GameScreen());

                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();

        hello.draw(batch);

        batch.end();
    }

    @Override
    public void hide() {
        super.hide();
    }
}

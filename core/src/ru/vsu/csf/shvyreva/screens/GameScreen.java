package ru.vsu.csf.shvyreva.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import ru.vsu.csf.shvyreva.ThreeInARow;
import ru.vsu.csf.shvyreva.model.Board;
import ru.vsu.csf.shvyreva.renderers.BoardRenderer;


public class GameScreen extends ThreeInARowScreen {

    BoardRenderer boardRenderer;

    @Override
    public void show() {
        super.show();
        boardRenderer = new BoardRenderer();

        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean mouseMoved(int screenX, int screenY) {

                return  true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                screenY = ThreeInARow.HEIGHT - screenY;
                screenX = ThreeInARow.WIDTH - screenX;

                boardRenderer.choiceCell(screenY, screenX);

                //добавить таймер

                //boardRenderer.board.recreate();

                return true;
            }

            @Override
            public boolean keyDown(int keycode) {

                switch (keycode) {
                    case Input.Keys.A:
                        boardRenderer.board.checkChain();
                        break;
                    case Input.Keys.S:
                        boardRenderer.board.moving();
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        boardRenderer.render(batch, delta);
        batch.begin();

        batch.end();
    }


    @Override
    public void hide() {
        super.hide();
    }
}

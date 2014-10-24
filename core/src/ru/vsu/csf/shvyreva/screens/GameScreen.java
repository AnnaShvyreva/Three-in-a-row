package ru.vsu.csf.shvyreva.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import ru.vsu.csf.shvyreva.renderers.BoardRenderer;


public class GameScreen extends ThreeInARowScreen {

    BoardRenderer boardRenderer;

    @Override
    public void show() {
        super.show();
        boardRenderer = new BoardRenderer();

        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                boardRenderer.board.recreate();
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
                //if (keycode == Input.Keys.A) {

                //}
                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        boardRenderer.render(batch);
        batch.begin();

        batch.end();
    }

    /*public  static void checkMoving(BoardCell cells, int i_1, int j_1, int i_2, int j_2){
        if ( ((i_1==i_2)&&( Math.abs(j_1-j_2)==1)) || ((j_1==j_2)&&( Math.abs(i_1-i_2)==1)) ){

        }
    }*/

    @Override
    public void hide() {
        super.hide();
    }
}

package ru.vsu.csf.shvyreva.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.vsu.csf.shvyreva.model.Board;
import ru.vsu.csf.shvyreva.model.BoardCell;


public class GameScreen extends ThreeInARowScreen {

    private static final float MARGIN_LEFT  = 73;
    private static final float MARGIN_TOP   = 25;
    private static final float CELL_SIZE    = 45;
    Sprite galaxy;


    private TextureRegion tile;
    private TextureRegion red;
    private TextureRegion green;
    private TextureRegion pink;
    private TextureRegion purple;
    private TextureRegion yellow;
    private BitmapFont font;
    private Board board;

    @Override
    public void show() {
        super.show();
        galaxy = new Sprite(new Texture(Gdx.files.internal("assets/galaxy.jpg")));


        tile = new TextureRegion(new Texture(Gdx.files.internal("assets/tile.png")));


        red = new TextureRegion(new Texture(Gdx.files.internal("assets/red.png")));
        green = new TextureRegion(new Texture(Gdx.files.internal("assets/green.png")));
        pink = new TextureRegion(new Texture(Gdx.files.internal("assets/pink.png")));
        purple = new TextureRegion(new Texture(Gdx.files.internal("assets/purple.png")));
        yellow = new TextureRegion(new Texture(Gdx.files.internal("assets/yellow.png")));

        font = new BitmapFont() {{
            setColor(Color.GREEN);}
            //setOwnsTexture(true);}
        };
        board = new Board();
        //board = Game.getInstance().getBoard();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        BoardCell[][] cells = board.getCells();

        int width = cells.length;
        int height = cells[0].length;

        batch.begin();
        galaxy.draw(batch);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                batch.draw(tile, MARGIN_LEFT + i * CELL_SIZE, MARGIN_TOP + j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                //font.draw(batch, i + " " + j, MARGIN_LEFT + i * CELL_SIZE + 2, MARGIN_TOP + j * CELL_SIZE);
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                
                switch (cells[j][i].color) {
                    case Red: {
                        batch.draw(red, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP + i * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Pink: {
                        batch.draw(pink, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP + i * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Yellow: {
                        batch.draw(yellow, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP + i * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Green: {
                        batch.draw(green, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP + i * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Purple: {
                        batch.draw(purple, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP + i * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                }
                //batch.draw(tile, MARGIN_LEFT + i * CELL_SIZE, MARGIN_TOP + j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                //font.draw(batch, i + " " + j, MARGIN_LEFT + i * CELL_SIZE + 2, MARGIN_TOP + j * CELL_SIZE);
            }
        }

        batch.end();
    }

    @Override
    public void hide() {
        super.hide();
    }
}

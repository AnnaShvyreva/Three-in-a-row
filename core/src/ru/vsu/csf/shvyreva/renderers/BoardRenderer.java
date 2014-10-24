package ru.vsu.csf.shvyreva.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.vsu.csf.shvyreva.model.Board;
import ru.vsu.csf.shvyreva.model.BoardCell;
import ru.vsu.csf.shvyreva.model.PieceColor;

public class BoardRenderer {

    private static final float MARGIN_LEFT  = 73;
    private static final float MARGIN_TOP   = 320;
    private static final float CELL_SIZE    = 45;

    Sprite galaxy;

    private TextureRegion tile;
    private TextureRegion red;
    private TextureRegion green;
    private TextureRegion pink;
    private TextureRegion purple;
    private TextureRegion yellow;

    public Board board;

    public BoardRenderer() {
        galaxy = new Sprite(new Texture(Gdx.files.internal("assets/galaxy.jpg")));

        tile = new TextureRegion(new Texture(Gdx.files.internal("assets/tile.png")));

        red = new TextureRegion(new Texture(Gdx.files.internal("assets/red.png")));
        green = new TextureRegion(new Texture(Gdx.files.internal("assets/green.png")));
        pink = new TextureRegion(new Texture(Gdx.files.internal("assets/pink.png")));
        purple = new TextureRegion(new Texture(Gdx.files.internal("assets/purple.png")));
        yellow = new TextureRegion(new Texture(Gdx.files.internal("assets/yellow.png")));

        board = Board.getBoard();
    }

    public void render(SpriteBatch batch){
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        final BoardCell[][] cells = board.getCells();

        int width = cells.length;
        int height = cells[0].length;

        batch.begin();

        galaxy.draw(batch);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                batch.draw(tile, MARGIN_LEFT + i * CELL_SIZE, MARGIN_TOP - j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[j][i].isEmpty()){
                    cells[j][i].setColor(PieceColor.Unknown);
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                switch (cells[j][i].color) {
                    case Red: {
                        batch.draw(red, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP - i * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Pink: {
                        batch.draw(pink, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP - i * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Yellow: {
                        batch.draw(yellow, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP - i * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Green: {
                        batch.draw(green, MARGIN_LEFT + j * CELL_SIZE + 5,MARGIN_TOP - i * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Purple: {
                        batch.draw(purple, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP - i * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Unknown:{
                        break;
                    }
                    default:
                        break;
                }

            }
        }
        batch.end();
    }

}

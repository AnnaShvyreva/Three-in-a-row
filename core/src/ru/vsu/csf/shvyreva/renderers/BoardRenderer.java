package ru.vsu.csf.shvyreva.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.vsu.csf.shvyreva.ThreeInARow;
import ru.vsu.csf.shvyreva.animators.Animator;
import ru.vsu.csf.shvyreva.model.Board;
import ru.vsu.csf.shvyreva.model.BoardCell;
import ru.vsu.csf.shvyreva.model.PieceColor;

import java.util.HashMap;

public class BoardRenderer {

    public static float MARGIN_LEFT;
    public static float MARGIN_TOP;
    public static final float CELL_SIZE    = 45;

    Sprite galaxy;

    public static TextureRegion tile;

    public static HashMap<PieceColor, TextureRegion> textures;

    /*public static TextureRegion red;
    public static TextureRegion green;
    public static TextureRegion pink;
    public static TextureRegion purple;
    public static TextureRegion yellow;*/

    public static TextureRegion select;
    public static TextureRegion boom;
    private BitmapFont font = new BitmapFont() {{setColor(Color.YELLOW);}};

    public Board board;

    public BoardRenderer() {
        galaxy = new Sprite(new Texture(Gdx.files.internal("assets/galaxy.jpg")));

        tile = new TextureRegion(new Texture(Gdx.files.internal("assets/tile.png")));

        select = new TextureRegion(new Texture(Gdx.files.internal("assets/select.png")));

        boom = new TextureRegion(new Texture(Gdx.files.internal("assets/boom.png")));

        textures = new HashMap<PieceColor, TextureRegion>() {{
            put(PieceColor.Red, new TextureRegion(new Texture(Gdx.files.internal("assets/red.png"))));
            put(PieceColor.Green, new TextureRegion(new Texture(Gdx.files.internal("assets/green.png"))));
            put(PieceColor.Pink, new TextureRegion(new Texture(Gdx.files.internal("assets/pink.png"))));
            put(PieceColor.Purple, new TextureRegion(new Texture(Gdx.files.internal("assets/purple.png"))));
            put(PieceColor.Yellow, new TextureRegion(new Texture(Gdx.files.internal("assets/yellow.png"))));
        }
        };
        /*red =
        green = new TextureRegion(new Texture(Gdx.files.internal("assets/green.png")));
        pink = new TextureRegion(new Texture(Gdx.files.internal("assets/pink.png")));
        purple = new TextureRegion(new Texture(Gdx.files.internal("assets/purple.png")));
        yellow = new TextureRegion(new Texture(Gdx.files.internal("assets/yellow.png")));*/

        board = Board.getBoard();

        MARGIN_LEFT  = (ThreeInARow.WIDTH - (CELL_SIZE*board.cells.length))/2;
        MARGIN_TOP   = ThreeInARow.HEIGHT - (ThreeInARow.HEIGHT - (CELL_SIZE * board.cells[0].length))/2;
        /*timerListener = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                board.checkChain();
                render(batch);
                //изменения
                //repaint();
            }
        };*/




    }

    public void choiceCell(int screenX, int screenY){

        if (((screenY<=480)&&(screenY>=121))&&((screenX>=43)&&(screenX<=357))) {
            int i = (int) ((screenY - MARGIN_LEFT) / CELL_SIZE);
            int j = (int) ((screenX - (ThreeInARow.HEIGHT - MARGIN_TOP)) / CELL_SIZE);
            //Gdx.app.log("", "screenX "+screenX+"screenY "+screenY);
            board.selectCell(i, j);
        }


    }


    public void render(SpriteBatch batch, float delta){
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        final BoardCell[][] cells = board.getCells();

        int width = cells.length;
        int height = cells[0].length;

        batch.begin();

        galaxy.draw(batch);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                batch.draw(tile, MARGIN_LEFT + i * CELL_SIZE, MARGIN_TOP - (j+1) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

        /*for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[j][i].isEmpty()){
                    cells[j][i].setColor(PieceColor.Unknown);
                }
            }
        }*/

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                //if (cells[j][i].color != PieceColor.Unknown)
                if (!cells[j][i].isEmpty())
                    batch.draw(textures.get(cells[j][i].color), MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP - (i+1) * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);

                /*switch (cells[j][i].color) {
                    case Red: {
                        batch.draw(red, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP - (i+1) * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Pink: {
                        batch.draw(pink, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP - (i+1) * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Yellow: {
                        batch.draw(yellow, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP - (i+1) * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Green: {
                        batch.draw(green, MARGIN_LEFT + j * CELL_SIZE + 5,MARGIN_TOP - (i+1) * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Purple: {
                        batch.draw(purple, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP - (i+1) * CELL_SIZE + 5, CELL_SIZE - 10, CELL_SIZE - 10);
                        break;
                    }
                    case Unknown:{
                        break;
                    }
                    default:
                        break;
                }*/

                /*if (cells[j][i].isToMove()) {
                    font.draw(batch, "!!!", MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP - (i+1) * CELL_SIZE + 5);
                }*/

            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[j][i].isSelect()){
                    batch.draw(select, MARGIN_LEFT + j * CELL_SIZE , MARGIN_TOP - (i+1) * CELL_SIZE, CELL_SIZE , CELL_SIZE );
                    //batch.draw(select, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP - (i+1) * CELL_SIZE + 5, 5, 5);
                    //batch.draw(select, MARGIN_LEFT + j * CELL_SIZE + 35, MARGIN_TOP - (i+1) * CELL_SIZE + 35, 5, 5);
                    //batch.draw(select, MARGIN_LEFT + j * CELL_SIZE + 35, MARGIN_TOP - (i+1) * CELL_SIZE + 5, 5, 5);
                    //batch.draw(select, MARGIN_LEFT + j * CELL_SIZE + 5, MARGIN_TOP - (i+1) * CELL_SIZE + 35, 5, 5);

                }
            }
        }

            Animator.getInstance().render(batch, delta);

                batch.end();
    }

}

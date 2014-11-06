package ru.vsu.csf.shvyreva.animators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vsu.csf.shvyreva.model.Board;
import ru.vsu.csf.shvyreva.model.FallingPiece;
import ru.vsu.csf.shvyreva.model.Point;
import ru.vsu.csf.shvyreva.renderers.BoardRenderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Animator {

    private static final float DESTROYING_DELAY = 0.37f;
    private static final float FALLING_SPEED = 1.2f;

    private AnimatorState state;
    private float destroyTimePassed;

    private ArrayList<Point> toDestroy;
    private HashMap<Integer, Integer> shiftValues;

    private ArrayList<FallingPiece> fallingPieces;


    private Animator() {
        toDestroy = new ArrayList<Point>();
        fallingPieces = new ArrayList<FallingPiece>();
        state = AnimatorState.DONE;
    }
    private static Animator instance;
    public static Animator getInstance() {
        if (instance == null)
            instance = new Animator();
        return instance;
    }


    public void init(Board board, ArrayList<Point> toDelete, ArrayList<Point> toMove) {
        this.state = AnimatorState.DESTROYING;
        this.destroyTimePassed = DESTROYING_DELAY;

        this.toDestroy = toDelete;

        shiftValues = new HashMap<Integer, Integer>();
        for (Point p : toDelete) {
            if (shiftValues.containsKey(p.getX()))
                shiftValues.put(p.getX(), shiftValues.get(p.getX()) + 1);
            else
                shiftValues.put(p.getX(), 1);
        }

        fallingPieces.clear();

        for (Point p : toMove) {
            fallingPieces.add(new FallingPiece(
                    BoardRenderer.MARGIN_LEFT + p.getX() * BoardRenderer.CELL_SIZE,
                    BoardRenderer.MARGIN_TOP - (p.getY()+1) * BoardRenderer.CELL_SIZE,
                    p.getX(),
                    board.cells[p.getX()][p.getY()].color
            ));
        }

        //if (fallingPieces.size() > 0)
        //anchor = fallingPieces.get(0).getY();

        //Gdx.app.log("animator", String.valueOf(anchor));

        /*int width = board.cells.length;
        int height = board.cells[0].length;*/

        /*for (int j=0; j<height; j++) {
            for (int i = 0; i < width; i++) {
                if (board.cells[i][j].isToDelete())
                    toDestroy.add(new Point(i, j));
            }
        }*/
    }

    public void render(SpriteBatch batch, float delta) {
        switch (state) {
            case DESTROYING:

                if (destroyTimePassed > 0) {
                    destroyTimePassed -= delta;

                    //batch.begin();

                    for (Point p : toDestroy) {
                        batch.draw(BoardRenderer.tile,  BoardRenderer.MARGIN_LEFT + p.getX() * BoardRenderer.CELL_SIZE, BoardRenderer.MARGIN_TOP - (p.getY()+1) * BoardRenderer.CELL_SIZE, BoardRenderer.CELL_SIZE, BoardRenderer.CELL_SIZE);
                        batch.draw(BoardRenderer.boom, BoardRenderer.MARGIN_LEFT + p.getX() * BoardRenderer.CELL_SIZE, BoardRenderer.MARGIN_TOP - (p.getY() + 1) * BoardRenderer.CELL_SIZE, BoardRenderer.CELL_SIZE, BoardRenderer.CELL_SIZE);
                    }
                    for (FallingPiece p : fallingPieces) {
                        batch.draw(BoardRenderer.textures.get(p.getColor()),
                                p.getX() + 5,
                                p.getY() + 5,
                                BoardRenderer.CELL_SIZE - 10,
                                BoardRenderer.CELL_SIZE - 10);
                    }
                    //toDestroy.clear();
                    //batch.end();
                }
                else {
                    //toDestroy.clear();
                    this.state = AnimatorState.MOVING;
                }
                break;
            case MOVING:
                /*if (Math.abs(fallingPieces.get(0).getY() - shiftValues.get(fallingPieces.get(0).getColumnIndex()) * anchor)
                        < BoardRenderer.CELL_SIZE) {*/

                // - distance

                boolean wasMovedAtLeastOne = false;
                for (FallingPiece p : fallingPieces) {

                    batch.draw(BoardRenderer.textures.get(p.getColor()),
                            p.getX() + 5,
                            p.getY() + 5,
                            BoardRenderer.CELL_SIZE - 10,
                            BoardRenderer.CELL_SIZE - 10);

                    if (p.getDistance() < shiftValues.get(p.getColumnIndex()) * BoardRenderer.CELL_SIZE) {
                        wasMovedAtLeastOne = true;
                        p.diminishY(FALLING_SPEED);
                    }
                }

                if (!wasMovedAtLeastOne) {
                    //Gdx.app.log("Animator", "Turned to DONE");
                    state = AnimatorState.DONE;
                    Board.getBoard().refresh(toDestroy);
                    toDestroy.clear();
                    //Board.getBoard().moving();
                }
                break;
        }
    }
}

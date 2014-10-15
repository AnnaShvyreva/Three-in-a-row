
package ru.vsu.csf.shvyreva.model;
import java.io.File;
import java.util.Random;

/**
 * Created by Анна on 28.09.2014.
 */


//доска представляет собой квадратную матрицу, для изощренности полей какие-то язейки не используются в уровнях

public class Board {


    private  BoardCell[][] cells;
    private Random random = new Random();

    public BoardCell[][] getCells() {
        return cells;
    }

    public Board() {

        int width = 10;
        int height = 8;
        cells = new BoardCell[width][height];

        for(int j=0; j<width; j++) {
            for (int i = 0; i < height; i++) {
                cells[j][i] = new BoardCell(true);
            }
        }

        for(int j=0; j<width; j++) {
            for (int i = 0; i < height; i++) {
                //PieceColor p = PieceColor.values()[random.nextInt(PieceColor.Purple.ordinal())];
                PieceColor p = PieceColor.values()[random.nextInt(PieceColor.values().length)];
                //System.out.print(cells[0][0]);

                cells[j][i].color = p;
            }
        }
    }

    public Board(File file) {
        //TODO: init


    }

    public void makeMove(int row, int col, Direction dir) {
        //TODO: make a move
    }

    protected void fallPieces(int[][] affectedArea) {
        //TODO: falling logic




    }
}

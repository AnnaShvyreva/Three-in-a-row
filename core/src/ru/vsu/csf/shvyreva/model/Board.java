
package ru.vsu.csf.shvyreva.model;

import com.badlogic.gdx.Gdx;
import com.sun.org.apache.bcel.internal.classfile.Unknown;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Анна on 28.09.2014.
 */


//доска представляет собой квадратную матрицу, для изощренности полей какие-то ячейки не используются в уровнях

public class Board {


    private  BoardCell[][] cells;

    private static Board instance;

    public BoardCell[][] getCells() {
        return cells;
    }

    private Board() {

        int width = 8;
        int height = 7;
        cells = new BoardCell[width][height];

        for(int j=0; j<width; j++) {
            for (int i = 0; i < height; i++) {
                cells[j][i] = new BoardCell(true);
                cells[j][i].setDelete(false);
            }
        }
        //randomCreate();

        /*cells[0][1].setDelete(true);
        cells[0][1].setEmpty(true);
        cells[0][1].setColor(PieceColor.Unknown);*/

        //int num=0;
        do {
            randomCreate();
            checkChain();
            //num = numEmpty(num);
            //Gdx.app.log("num ", num+"\n");
            //numEmpty();
            moving();
        }while (numEmpty()!=0);

    }

    public int numEmpty(){

        int num=0;
        int width = cells.length;
        int height = cells[0].length;

        for(int j=0; j<width; j++) {
            for (int i = 0; i < height; i++) {
                if (cells[j][i].isEmpty()) num++;
            }
        }
        Gdx.app.log("num ", num+"\n");
        return num;
    }

    public void randomCreate()
    {
        Random random = new Random();
        int height = cells[0].length;

        for (BoardCell[] cell : cells) {
            for (int i = 0; i < height; i++) {
                //if (cell[i].isEmpty()) {
                    cell[i].color = PieceColor.values()[random.nextInt(PieceColor.values().length - 1) + 1];
                    cell[i].setEmpty(false);
                //}
            }
        }
    }

    public void checkChain(){

        ArrayList<Point> toDelete = new ArrayList<Point>();

        int width = cells.length;
        int height = cells[0].length;

        PieceColor lastColor = PieceColor.Unknown;
        PieceColor nowColor;
        int chainLength = 1;

        for (int j=0; j<height; j++) {

            chainLength = 1;

            for (int i = 0; i < width; i++) {
                nowColor = cells[i][j].color;

                if (nowColor == lastColor) {
                    chainLength++;
                } else {
                    if (chainLength > 2) {
                        Gdx.app.log("Game", "Chain found at i = " + i + "; j = " + j);

                        for (int k=i-1; k >= i - chainLength; k--){
                            //cells[k][j].setDelete(true);
                            //cells[k][j].setEmpty(true);
                            toDelete.add(new Point(k, j));
                        }
                    }

                    chainLength = 1;
                    lastColor = nowColor;
                }
            }

            if (chainLength > 2) {

                Gdx.app.log("Game", "Chain found at i = " + (width - chainLength) + "; j = " + j);

                for (int k=width-1; k >= width - chainLength; k--){
                    //cells[k][j].setDelete(true);
                    //cells[k][j].setEmpty(true);
                    toDelete.add(new Point(k, j));
                }
            }
            lastColor = PieceColor.Unknown;
        }

        if (chainLength > 2) {

            Gdx.app.log("Game", "Last Chain found");

            for (int k = width - 1; k >= width - chainLength; k--){
                //cells[k][j].setDelete(true);
                //cells[k][j].setEmpty(true);
                toDelete.add(new Point(k, height - 1));
            }
        }



        for (int j=0; j<width; j++) {
            chainLength = 1;
            for (int i = 0; i < height; i++) {
                nowColor = cells[j][i].color;
                if (nowColor == lastColor) {
                    chainLength++;
                } else {
                    if (chainLength > 2) {
                        for (int k=i-1; k>=i-chainLength;k--){
                            //Gdx.app.log("err3", "i = "+i+" k= "+k+" j= "+j+" chainLength = "+chainLength+"\n");
                            //cells[j][k].setDelete(true);
                            //cells[j][k].setEmpty(true);
                            toDelete.add(new Point(j, k));
                        }
                    }
                    chainLength = 1;
                    lastColor = nowColor;
                }
            }
            if (chainLength > 2) {
                for (int k=height-1; k>=height-chainLength;k--){
                    //Gdx.app.log("err4", " k= "+k+" j= "+j+" chainLength = "+chainLength+"\n");
                    //cells[j][k].setDelete(true);
                    //cells[j][k].setEmpty(true);
                    toDelete.add(new Point(j, k));
                }
            }
            lastColor = PieceColor.Unknown;
        }

        for (Point p : toDelete) {
            cells[p.getX()][p.getY()].setEmpty(true);
            cells[p.getX()][p.getY()].setDelete(true);
        }
    }



    public void moving() {
        int width = cells.length;
        int height = cells[0].length;

        for (int i= 0; i < width; i++) {
            for (int j = height - 1; j >= 1; j--) {
                //Gdx.app.log("errMoving", "i= "+i+" j= "+j);
                if (!cells[i][j].isEmpty)
                    continue; //если занята

                boolean bool = false;               //если пустая, находим первую непустую и двигаем ее, а там удаляем

                for (int k = j-1; k >=0; k--) {
                    if(!cells[i][k].isEmpty) {
                        bool=true;                  //нашли непустую
                        cells[i][j].setColor(cells[i][k].getColor());
                        cells[i][j].setEmpty(false);

                        cells[i][k].setEmpty(true);
                        cells[i][k].setColor(PieceColor.Unknown);
                        break;
                    }
                }
                if (!bool) break;                   //если не нашли непустых
            }
        }
    }

    public static Board getBoard(){
        if (instance == null)
            instance = new Board();
        return instance;
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

    public void recreate() {
        randomCreate();
        Gdx.app.log("Game", "ksjgn");
    }
}

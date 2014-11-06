
package ru.vsu.csf.shvyreva.model;

import com.badlogic.gdx.Gdx;
import ru.vsu.csf.shvyreva.animators.Animator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Анна on 28.09.2014.
 */


//доска представляет собой квадратную матрицу, для изощренности полей какие-то ячейки не используются в уровнях

public class Board {

    private static final Random random = new Random();

    public  BoardCell[][] cells;
    public Boolean click = false;
    public SelectedCell firstClick;
    public SelectedCell secondClick;

    private static Board instance;

    private boolean canAnimate = false;

    public BoardCell[][] getCells() {
        return cells;
    }

    private Board() {
        int width = 7;
        int height = 5;
        cells = new BoardCell[width][height];

        for(int j=0; j<width; j++) {
            for (int i = 0; i < height; i++) {
                cells[j][i] = new BoardCell(true);
                cells[j][i].setSelect(false);
            }
        }

        do {
            randomCreate();
            checkChain();
            moving();
        } while (numEmpty()!=0);
    }

    public void selectCell(int i, int j){
        int x = cells.length - i-1;
        int y = cells[0].length - j-1;
        cells[x][y].setSelect(true);
        //Gdx.app.log("","Color = "+ cells[x][y].getColor());

        for(int k=0; k<cells.length; k++) {
            for (int m = 0; m < cells[0].length; m++) {
                if ((cells[k][m].isSelect())&((k!=x)||(m!=y))) cells[k][m].setSelect(false);
            }
        }

        if (click) {
            secondClick = new SelectedCell(x,y);
            canAnimate = true;
            changeCells();
            click = false;
            cells[x][y].setSelect(false);
        }
        else {
            click=true;
            firstClick = new SelectedCell(x,y);
        }

    }

    public void changeCells(){

        if (((firstClick.getI()==secondClick.getI())&&(Math.abs(firstClick.getJ() - secondClick.getJ())==1))||
                ((firstClick.getJ()==secondClick.getJ())&&(Math.abs(firstClick.getI()-secondClick.getI())==1))) {

            PieceColor p = cells[firstClick.getI()][firstClick.getJ()].getColor();
            cells[firstClick.getI()][firstClick.getJ()].setColor(cells[secondClick.getI()][secondClick.getJ()].getColor());
            cells[secondClick.getI()][secondClick.getJ()].setColor(p);

            checkChain();
            /*if (numEmpty()!=0) moving();
            else {
                cells[secondClick.getI()][secondClick.getJ()].setColor(cells[firstClick.getI()][firstClick.getJ()].getColor());
                cells[firstClick.getI()][firstClick.getJ()].setColor(p);
            }*/
        }
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
        //Gdx.app.log("num ", num+"\n");
        return num;
    }

    public void randomCreate()
    {
        int height = cells[0].length;

        for (BoardCell[] cell : cells) {
            for (int i = 0; i < height; i++) {
                if (cell[i].isEmpty()) {
                    cell[i].color = PieceColor.values()[random.nextInt(PieceColor.values().length - 1) + 1];
                    cell[i].setEmpty(false);
                }
            }
        }
    }

    public void checkChain() {
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
                        //Gdx.app.log("Game", "Chain found at i = " + i + "; j = " + j);

                        for (int k=i-1; k >= i - chainLength; k--){
                            toDelete.add(new Point(k, j));
                        }
                    }

                    chainLength = 1;
                    lastColor = nowColor;
                }
            }

            if (chainLength > 2) {

                //Gdx.app.log("Game", "Chain found at i = " + (width - chainLength) + "; j = " + j);

                for (int k=width-1; k >= width - chainLength; k--){
                    toDelete.add(new Point(k, j));
                }
            }
            lastColor = PieceColor.Unknown;
        }

        if (chainLength > 2) {
            for (int k = width - 1; k >= width - chainLength; k--){
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
                            toDelete.add(new Point(j, k));
                        }
                    }
                    chainLength = 1;
                    lastColor = nowColor;
                }
            }
            if (chainLength > 2) {
                for (int k=height-1; k>=height-chainLength;k--){
                    toDelete.add(new Point(j, k));
                }
            }
            lastColor = PieceColor.Unknown;
        }

        ArrayList<Point> toMove = setCellsToMove(toDelete);

        for (Point p : toDelete) {
            cells[p.getX()][p.getY()].setEmpty(true);
        }
        for (Point p : toMove) {
            cells[p.getX()][p.getY()].setEmpty(true);
        }

        if (canAnimate && toDelete.size() > 0)
            Animator.getInstance().init(this, toDelete, toMove);

    }

    public ArrayList<Point> setCellsToMove(ArrayList<Point> cellsToDelete) {

        ArrayList<Point> result = new ArrayList<Point>();

        for (Point  p : cellsToDelete) {
            if (p.getY() == 0)
                continue;
            for (int i = p.getY()-1; i >= 0; i--) {
                if (!cellsToDelete.contains(new Point(p.getX(), i))) {
                    result.add(new Point(p.getX(), i));
                }
            }
        }

        return result;
    }

    public void refresh(ArrayList<Point> toDelete) {
        for (int i = 0; i < cells[0].length; ++i) {
            for (BoardCell[] cell : cells) {
                cell[i].setEmpty(false);
            }
        }

        for (Point p : toDelete) {
            cells[p.getX()][p.getY()].setEmpty(true);
        }

        do {
            moving();
            randomCreate();
            checkChain();
        } while (numEmpty()!=0);
    }

    public void moving() {
        int width = cells.length;
        int height = cells[0].length;

        for (int i= 0; i < width; i++) {
            for (int j = height - 1; j >= 1; j--) {
                if (!cells[i][j].isEmpty)
                    continue; //если занята

                boolean bool = false;               //если пустая, находим первую непустую и двигаем ее, а там удаляем

                for (int k = j-1; k >=0; k--) {
                    if(!cells[i][k].isEmpty) {
                        bool=true;                  //нашли непустую
                        cells[i][j].setColor(cells[i][k].getColor());
                        cells[i][j].setEmpty(false);

                        cells[i][k].setEmpty(true);
                        //cells[i][k].setColor(PieceColor.Unknown);
                        break;
                    }
                }
                if (!bool) break;                   //если не нашли непустых
            }
        }


        //Gdx.app.log("Moving", "Сделал, что мог :(");
    }

    public static Board getBoard(){
        if (instance == null)
            instance = new Board();
        return instance;
    }

    public boolean noCombinations(){

        int width = cells.length;
        int height = cells[0].length;

        int flag = 0;

        for (int i= 0; i < width; i++) {
            for (int j = 0; j<height; j++) {
                PieceColor c = cells[i][j].getColor();
                if(i<width-3) {
                    if (((cells[i + 1][j].getColor() == c)||(cells[i + 2][j].getColor() == c)) && (cells[i + 3][j].getColor() == c)){
                        flag++;
                        break;
                    }
                }
                if (j<height-3) {
                    if (((cells[i][j + 1].getColor() == c)||(cells[i][j + 2].getColor() == c)) && (cells[i][j + 3].getColor() == c)){
                        flag++;
                        break;
                    }
                }
                if ((i<width-2)&&(j>0)&&(j<height-1)) {
                    if (((cells[i + 1][j + 1].getColor() == c)||(cells[i + 1][j - 1].getColor() == c)) && (cells[i + 2][j].getColor() == c)) {
                        flag++;
                        break;
                    }
                }
                if ((i>0)&&(i<width-1)&&(j<height-2)) {
                    if (((cells[i + 1][j + 1].getColor() == c)||(cells[i - 1][j + 1].getColor() == c))&& (cells[i][j + 2].getColor() == c)){
                        flag++;
                        break;
                    }
                }
                if ((i<width-2)&&(j>0)&&(j<height-1)) {
                    if ((cells[i + 1][j].getColor() == c) && ((cells[i + 2][j + 1].getColor() == c) || (cells[i + 2][j - 1].getColor() == c))) {
                        flag++;
                        break;
                    }
                }
                if ((i<width-1)&&(i>0)&&(j>0)&&(j<height-1)) {
                    if ((cells[i + 1][j].getColor() == c) && ((cells[i - 1][j + 1].getColor() == c) || (cells[i - 1][j - 1].getColor() == c))) {
                        flag++;
                        break;
                    }
                }
                if((i>0)&&(i<width-1)&&(j<height-2)) {
                    if ((cells[i][j + 1].getColor() == c) && ((cells[i + 1][j + 2].getColor() == c) || (cells[i - 1][j + 2].getColor() == c))) {
                        flag++;
                        break;
                    }
                }
                if((i>0)&&(i<width-1)&&(j<height-1)&&(j>0)) {
                    if ((cells[i][j + 1].getColor() == c) && ((cells[i + 1][j - 1].getColor() == c) || (cells[i - 1][j - 1].getColor() == c))) {
                        flag++;
                        break;
                    }
                }
            }
            if (flag != 0){
                break;
            }

        }
        if (flag == 0) {
            return true;//комбинаций нет
        }
        else return false; //комбинации есть
    }


    public void cleanBoard(){
        int width = cells.length;
        int height = cells[0].length;

        for (int i= 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].setEmpty(true);
                //cells[i][j].setToDelete(true);
            }
        }
    }

    public void recreate() {

        moving();


        /*if (noCombinations()){
            /////////////////////////////////////////////
            // всплывающее окно о том, что нет комбинаций
            /////////////////////////////////////////////
            Gdx.app.log("нет комбинаций"," ");
            cleanBoard();
            recreate();
        }*/
    }
}

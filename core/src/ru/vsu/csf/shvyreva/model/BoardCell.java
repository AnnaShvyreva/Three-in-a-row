package ru.vsu.csf.shvyreva.model;

public class BoardCell {

    public boolean isEmpty;
    public PieceColor color;
    //private Piece piece;

    /*public boolean toDelete;
    public boolean toMove;*/

    public boolean select;


    /*public boolean isToMove() {
        return toMove;
    }

    public void setToMove(boolean toMove) {
        this.toMove = toMove;
    }*/

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    /*public boolean isToDelete() {
        return toDelete;
    }

    public void setToDelete(boolean toDelete) {
        this.toDelete = toDelete;
    }*/

    public BoardCell(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public void setColor (PieceColor pieceColor){
        this.color = pieceColor;
    }

    public PieceColor getColor (){
        return color;
    }

    /*public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }*/
}

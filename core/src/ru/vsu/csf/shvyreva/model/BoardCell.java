package ru.vsu.csf.shvyreva.model;

public class BoardCell {

    private boolean isEmpty;
    public PieceColor color;
    private Piece piece;

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

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}

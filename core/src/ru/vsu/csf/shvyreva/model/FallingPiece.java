package ru.vsu.csf.shvyreva.model;

public class FallingPiece {

    private float x, y;
    private float initialY;
    private int columnIndex;
    private PieceColor color;

    public FallingPiece(float x, float y, int columnIndex, PieceColor color) {
        this.x = x;
        this.y = y;
        this.initialY = y;
        this.columnIndex = columnIndex;
        this.color = color;
    }

    public float getDistance() {
        return Math.abs(initialY - y);
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void diminishY(float delta) {
        this.y -= delta;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }
}

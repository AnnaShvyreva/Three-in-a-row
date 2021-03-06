package ru.vsu.csf.shvyreva.model;

/**
 * Created by Анна on 24.10.2014.
 */
public class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Point p = (Point) obj;
        return (p.getX() == x && p.getY() == y);
    }
}

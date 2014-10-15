package ru.vsu.csf.shvyreva.model;

//хранит информацию о текущем пользователе, обновляет ее
public class Player {

    private int score;
    private int passedLevelCount;
    private String nickName;

    public Player(String nick) {
        score = 0;
        passedLevelCount = 0;
        nickName = nick;
    }

    public int getPassedLevelCount() {
        return passedLevelCount;
    }

    public void setPassedLevelCount(int passedLevelCount) {
        this.passedLevelCount = passedLevelCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

}

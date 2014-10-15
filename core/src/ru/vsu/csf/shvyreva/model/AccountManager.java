package ru.vsu.csf.shvyreva.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Анна on 28.09.2014.
 */

//отвечает за загрузку информации о конкретном пользователе

public class AccountManager {

    //region Singleton
    private static AccountManager instance;

    private AccountManager() {
        players = new ArrayList<Player>();
    }

    public static AccountManager getManager() {
        if (instance == null)
            instance = new AccountManager();
        return instance;
    }
    //endregion


    List<Player> players;


    public boolean loadData(File f) {
        try {
            //TODO: read from file, add to list of players

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean saveData(File f) {
        try {
            //TODO: write to file

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void chooseProfile(int index) {
        Game.getGame().setPlayer(players.get(index));
    }

}

package com.mycompany.lol_api;

import java.util.List;

public class MatchSingle {
    
    private int gameDuration;
    private String gameMode;
    private String gameType;
    private String win;
    private String defeat;
    private int killsTeam0;
    private int killsTeam1;
    private List<Player> playersTeam1;
    private List<Player> playersTeam2;

    public MatchSingle(int gameDuration, String gameMode, String gameType, String win, String defeat, int killsTeam0, int killsTeam1, List<Player> playersTeam1, List<Player> playersTeam2) {
        this.gameDuration = gameDuration;
        this.gameMode = gameMode;
        this.gameType = gameType;
        this.win = win;
        this.defeat = defeat;
        this.killsTeam0 = killsTeam0;
        this.killsTeam1 = killsTeam1;
        this.playersTeam1 = playersTeam1;
        this.playersTeam2 = playersTeam2;
    }
    
    public int getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public int getKillsTeam0() {
        return killsTeam0;
    }

    public void setKillsTeam0(int killsTeam0) {
        this.killsTeam0 = killsTeam0;
    }

    public int getKillsTeam1() {
        return killsTeam1;
    }

    public void setKillsTeam1(int killsTeam1) {
        this.killsTeam1 = killsTeam1;
    }

    public String getDefeat() {
        return defeat;
    }

    public void setDefeat(String defeat) {
        this.defeat = defeat;
    }

    public List<Player> getPlayersTeam1() {
        return playersTeam1;
    }

    public void setPlayersTeam1(List<Player> playersTeam1) {
        this.playersTeam1 = playersTeam1;
    }

    public List<Player> getPlayersTeam2() {
        return playersTeam2;
    }

    public void setPlayersTeam2(List<Player> playersTeam2) {
        this.playersTeam2 = playersTeam2;
    }
    
    
    
    
    
    
    
    
    

}

package com.mycompany.lol_api;

public class Player {
    
    private int kills;
    private String name;
    private int championId;
    private int teamId;

    public Player(int kills, String name, int championId, int teamId) {
        this.kills = kills;
        this.name = name;
        this.championId = championId;
        this.teamId = teamId;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
    
    
    

}

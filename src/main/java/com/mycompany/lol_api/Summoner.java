package com.mycompany.lol_api;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

public class Summoner {
    
    private int id;
    private long accountId;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private int summonerLevel;

    public Summoner(int id, long accountId, String name, int profileIconId, long revisionDate, int summonerLevel) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }
    
    

}

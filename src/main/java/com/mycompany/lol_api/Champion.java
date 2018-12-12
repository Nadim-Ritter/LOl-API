package com.mycompany.lol_api;

public class Champion {
    
    private String imgUrl;
    private String name;
    private String title;
    private String blurb;
    private int attack;
    private int defense;
    private int magic;
    private int difficulty;
   

    public Champion(){
        
    }

    public Champion(String imgUrl, String name, String title, String blurb, int attack, int defense, int magic, int difficulty) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.title = title;
        this.blurb = blurb;
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.difficulty = difficulty;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    
    
    
    

}

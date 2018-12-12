package com.mycompany.lol_api;

import java.util.ArrayList;
import java.util.List;

public class ChampionsList {
    
    private String type;
    private String format;
    private String version;
    private List<String> data;

    public ChampionsList() {
    }

    public ChampionsList(String type, String format, String version, List<String> data) {
        this.type = type;
        this.format = format;
        this.version = version;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
    
    
    
    

}

package com.mycompany.lol_api;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "SummonerSendName")
@ViewScoped

public class SummonerSendName {
    
    private String name;
    
    
    public SummonerSendName(){
        
    }

    public SummonerSendName(String name) {
        this.name = name;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

}

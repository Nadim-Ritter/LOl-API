package com.mycompany.lol_api;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import org.json.JSONObject;

@ManagedBean(name = "GetApiData")
@SessionScoped

public class GetApiData {
    
    private List<Summoner> summonersList = new ArrayList();;
    

    public void getSummonersData(String name) throws MalformedURLException, IOException {

        String apiKey = "RGAPI-c410d722-2146-4e29-b4e6-e546101dae20";
        String url = "https://euw1.api.riotgames.com/lol/summoner/v3/summoners/by-name/" + name + "?api_key=" + apiKey;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        Gson gson = new Gson();
        
        Summoner summoner = gson.fromJson(response.toString(), Summoner.class);

        summonersList.add(summoner);

    }
    
    public void getChmapionData() throws MalformedURLException, IOException{
        
        //http://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        //https://developer.riotgames.com/static-data.html
        //https://developer.riotgames.com/api-methods/#champion-mastery-v3/GET_getAllChampionMasteries
        
        String apiKey = "RGAPI-c410d722-2146-4e29-b4e6-e546101dae20";
        String urlAllChampionNames = "http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/champion.json";

        URL obj = new URL(urlAllChampionNames);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + urlAllChampionNames);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
       

        JSONObject championsJson = new JSONObject(response.toString());
        
        System.out.println("-----------" + championsJson.getJSONObject("data").getJSONObject("Akali"));
       
        

        
        
        
        
    }

    public List<Summoner> getSummonersList() {
        return summonersList;
    }

    public void setSummonersList(List<Summoner> summonersList) {
        this.summonersList = summonersList;
    }
    
    
    
    

}

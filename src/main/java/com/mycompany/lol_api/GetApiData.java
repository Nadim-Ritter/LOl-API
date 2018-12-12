package com.mycompany.lol_api;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import org.json.JSONArray;
import org.json.JSONObject;

@ManagedBean(name = "GetApiData")
@SessionScoped

public class GetApiData {

    private List<Summoner> summonersList = new ArrayList();
    private List<Champion> championsList = new ArrayList();
    

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

    public void getChmapionData() throws MalformedURLException, IOException {

        //http://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        //https://developer.riotgames.com/static-data.html
        //https://developer.riotgames.com/api-methods/#champion-mastery-v3/GET_getAllChampionMasteries
        String apiKey = "RGAPI-c410d722-2146-4e29-b4e6-e546101dae20";
        String championData = "http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/champion.json";

        URL obj = new URL(championData);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + championData);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        //System.out.println("-----------" + championsJson.getJSONObject("data").getJSONObject("Akali"));
        //get champion names
        List<String> championNames = new ArrayList();
        
        JSONObject championsJson = new JSONObject(response.toString());

        for (Object key : championsJson.getJSONObject("data").keySet()) {
            //based on you key types
            String keyStr = (String) key;
            Object keyvalue = championsJson.getJSONObject("data").get(keyStr);

            //Print key and value
            System.out.println(keyStr);
            championNames.add(keyStr);
            //displayPictures(keyStr);
        }
        
        //load champion data into arraylist
        for(int i = 0; i < championNames.size(); i++){
            Champion champ = new Champion("http://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + championNames.get(i) + "_0.jpg", championNames.get(i), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getString("title"), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getString("blurb"), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getJSONObject("info").getInt("attack"), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getJSONObject("info").getInt("defense"), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getJSONObject("info").getInt("magic"), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getJSONObject("info").getInt("difficulty"));
            championsList.add(champ);
        }

        
    }

    public void displayPictures(String name) throws MalformedURLException, IOException{
        
        String urlAllChampionNames = "http://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + name + "_0.jpg";

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
        
        System.out.println(response.toString());
        
        
        
    }    
    
    
    
    
    public List<Summoner> getSummonersList() {
        return summonersList;
    }

    public void setSummonersList(List<Summoner> summonersList) {
        this.summonersList = summonersList;
    }

    public List<Champion> getChampionsList() {
        return championsList;
    }

    public void setChampionsList(List<Champion> championsList) {
        this.championsList = championsList;
    }

    
    
    

}

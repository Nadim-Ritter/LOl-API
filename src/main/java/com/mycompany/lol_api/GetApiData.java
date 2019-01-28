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
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import org.json.JSONObject;

@ManagedBean(name = "GetApiData")
@SessionScoped

public class GetApiData {

    private List<Summoner> summonersList = new ArrayList();
    private List<Champion> championsList = new ArrayList();
    private List<Long> gameIdList = new ArrayList();
    private List<MatchSingle> matchesList = new ArrayList();
    private Champion currentChampion;
    private Summoner currentSummoner;
    String apiKey = "RGAPI-590934eb-d7ca-41de-afd8-65aa571b6085";

    public StringBuffer apiCall(String url) throws IOException {

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

        return response;
    }

    public void getSummonersData(String name) throws MalformedURLException, IOException {

        if (!name.equals("")) {
            String url = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + name + "?api_key=" + apiKey;

            Gson gson = new Gson();

            Summoner summoner = gson.fromJson(apiCall(url).toString(), Summoner.class);

            summonersList.add(summoner);
        }

    }

    public void getChampionData() throws MalformedURLException, IOException {

        //http://ddragon.leagueoflegends.com/cdn/img/champion/loading/Aatrox_0.jpg
        //https://developer.riotgames.com/static-data.html
        //https://developer.riotgames.com/api-methods/#champion-mastery-v3/GET_getAllChampionMasteries
        String url = "http://ddragon.leagueoflegends.com/cdn/9.2.1/data/en_US/champion.json";

        List<String> championNames = new ArrayList();

        JSONObject championsJson = new JSONObject(apiCall(url).toString());

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
        for (int i = 0; i < championNames.size(); i++) {
            Champion champ = new Champion("http://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + championNames.get(i) + "_0.jpg", championNames.get(i), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getString("title"), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getString("blurb"), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getJSONObject("info").getInt("attack"), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getJSONObject("info").getInt("defense"), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getJSONObject("info").getInt("magic"), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getJSONObject("info").getInt("difficulty"), championsJson.getJSONObject("data").getJSONObject(championNames.get(i)).getInt("key"));
            championsList.add(champ);
        }

    }

    public void setSingelChampionData(String name) throws MalformedURLException, IOException {

        for (int i = 0; i < championsList.size(); i++) {
            if (championsList.get(i).getName().equals(name)) {
                currentChampion = championsList.get(i);
            }
        }

    }

    public void getMatches(Summoner currentSummoner) throws IOException {
        String accountID = currentSummoner.getAccountId();
        String url = "https://euw1.api.riotgames.com/lol/match/v4/matchlists/by-account/" + accountID + "?api_key=" + apiKey;

        JSONObject matchesList = new JSONObject(apiCall(url).toString());

        for (int i = 0; i < matchesList.getJSONArray("matches").length(); i++) {
            JSONObject singleMatch = new JSONObject(matchesList.getJSONArray("matches").get(i).toString());
            gameIdList.add(singleMatch.getLong("gameId"));
        }
        System.out.println("test");
    }

    public void getSingleMatchDetails() throws MalformedURLException, IOException {
        for (int i = 0; i < 10; i++) {
            String url = "https://euw1.api.riotgames.com/lol/match/v4/matches/" + gameIdList.get(i) + "?api_key=" + apiKey;
            System.out.println(url);
            JSONObject matchesListDetails = new JSONObject(apiCall(url).toString());
            List<Player> players = new ArrayList();

            for (int y = 0; y < matchesListDetails.getJSONArray("participants").length(); y++) {
                JSONObject participants = new JSONObject(matchesListDetails.getJSONArray("participants").get(y).toString());
                JSONObject participantIdentities = new JSONObject(matchesListDetails.getJSONArray("participantIdentities").get(y).toString());

                Player player = new Player(participants.getJSONObject("stats").getInt("kills"), participantIdentities.getJSONObject("player").getString("summonerName"), participants.getInt("championId"), participants.getInt("teamId"));
                players.add(player);
            }

            List<Player> playersTeam1 = new ArrayList();
            List<Player> playersTeam2 = new ArrayList();

            for (int q = 0; q < players.size(); q++) {
                if (players.get(q).getTeamId() == 100) {
                    playersTeam1.add(players.get(q));
                } else {
                    playersTeam2.add(players.get(q));
                }
            }

            String teamWin;
            String teamDefeat;

            JSONObject team0 = new JSONObject(matchesListDetails.getJSONArray("teams").getJSONObject(0).toString());

            if (team0.getString("win").equals("Win")) {
                teamWin = "team0";
                teamDefeat = "team1";
            } else {
                teamWin = "team1";
                teamDefeat = "team0";
            }

            int killsTeam0 = 0;
            int killsTeam1 = 0;

            for (int x = 0; x < players.size(); x++) {
                if (players.get(x).getTeamId() == 100) {
                    killsTeam0 += players.get(x).getKills();
                } else {
                    killsTeam1 += players.get(x).getKills();
                }
            }

            MatchSingle match = new MatchSingle(matchesListDetails.getInt("gameDuration"), matchesListDetails.getString("gameMode"), matchesListDetails.getString("gameType"), teamWin, teamDefeat, killsTeam0, killsTeam1, playersTeam1, playersTeam2);

            matchesList.add(match);
        }
    }

    public String getImgUrl(int championId) {
        String imgUrl = "";
        for (int i = 0; i < championsList.size(); i++) {
            if (championsList.get(i).getKey() == championId) {
                imgUrl = championsList.get(i).getImgUrl();
                break;
            }
        }
        return imgUrl;
    }

    public boolean checkIfTeam1(int id) {
        if (id == 100) {
            return true;
        }
        return false;
    }

    public boolean checkIfTeam2(int id) {
        if (id == 200) {
            return true;
        }
        return false;
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

    public Champion getCurrentChampion() {
        return currentChampion;
    }

    public void setCurrentChampion(Champion currentChampion) {
        this.currentChampion = currentChampion;
    }

    public Summoner getCurrentSummoner() {
        return currentSummoner;
    }

    public void setCurrentSummoner(Summoner currentSummoner) {
        this.currentSummoner = currentSummoner;
    }

    public List<Long> getGameIdList() {
        return gameIdList;
    }

    public void setGameIdList(List<Long> gameIdList) {
        this.gameIdList = gameIdList;
    }

    public List<MatchSingle> getMatchesList() {
        return matchesList;
    }

    public void setMatchesList(List<MatchSingle> matchesList) {
        this.matchesList = matchesList;
    }

}

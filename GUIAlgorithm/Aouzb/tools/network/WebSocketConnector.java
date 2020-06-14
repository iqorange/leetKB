package GUIAlgorithm.Aouzb.tools.network;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import GUIAlgorithm.Aouzb.tools.view.KBData;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;


public class WebSocketConnector implements WSSConnection{
    private String URL;
    private WebSocketClient webSocketClient;
    private KBData data;
    private Map<Integer, Gamer> gamers;
    private boolean linkStatus;
    private Queue<Integer> removedIndex;

    public WebSocketConnector(String URL, KBData data) {
        gamers = new ConcurrentHashMap<>();
        this.URL = URL;
        this.data = data;
        this.linkStatus = false;
        this.removedIndex = new LinkedBlockingQueue<>();
    }

    @Override
    public boolean StartLink(){
        try {
            URI uri = new URI(URL);
            WebSocketClient webSocketClient = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("Link Succeed~");
                    linkStatus = true;
                }

                @Override
                public void onMessage(String s) {
                    // TODO 1.Gamers link  2.Gamers change direction
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    linkStatus = false;
                }

                @Override
                public void onError(Exception e) {
                    linkStatus = false;
                }
            };
            webSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Gamer setGamer(String gamerName){
        if (removedIndex.isEmpty()){
            return gamers.put(gamers.size(), new Gamer(gamers.size(), gamerName));
        }
        int index = removedIndex.remove();
        return gamers.put(index, new Gamer(index, gamerName));
    }

    private Gamer getGamer(int gamerID){
        if (!inGamers(gamerID)) {
            throw new IllegalArgumentException("Gamer ID is not matching!");
        }
        return gamers.get(gamerID);
    }

    private void removeGamer(int gamerID){
        gamers.remove(gamerID);
        removedIndex.add(gamerID);
    }

    @Override
    public synchronized Iterator<Gamer> scoreList(){
        if (gamers.size() == 0){
            return null;
        }
        List<Gamer> sortedScoreList = new ArrayList<>(gamers.values());
        sortedScoreList.sort((o1, o2) -> o2.getScore() - o1.getScore());
        return sortedScoreList.listIterator();
    }

    @Override
    public int getLinkedGamerNumber(){
        return gamers.size();
    }

    @Override
    public void closeLink(){
        webSocketClient.close();
        webSocketClient = null;
    }

    @Override
    public KBData getData() {
        return data;
    }

    @Override
    public void setData(KBData data) {
        this.data = data;
    }

    @Override
    public boolean isReady(){
        return gamers.size() == 2;
    }

    @Override
    public boolean isReady(int readyMember){
        return gamers.size() == readyMember;
    }

    @Override
    public void changeGamerDirection(int gamerID, Directions direction){
        if (inGamers(gamerID)) gamers.get(gamerID).changeDirection(direction);
    }

    @Override
    public Directions getGamerDirection(int gamerID){
        if (!inGamers(gamerID)) {
            throw new IllegalArgumentException("Gamer ID is not matching!");
        }
        return gamers.get(gamerID).getGamerDirection();
    }

    private boolean inGamers(int gamerID){
        if (gamers.get(gamerID) == null || gamers.get(gamerID).getGamerID() != gamerID){
            return false;
        }
        return true;
    }

    @Override
    public void changeGamerScore(int gamerID, ScorePower scorePower){
        if (inGamers(gamerID)){
            gamers.get(gamerID).changeScore(scorePower);
        }
    }

    @Override
    public int getGamerScore(int gameID){
        if (!inGamers(gameID)){
            throw new IllegalArgumentException("Gamer ID is not matching!");
        }
        return gamers.get(gameID).getScore();
    }
}

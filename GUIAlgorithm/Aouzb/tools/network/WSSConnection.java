package GUIAlgorithm.Aouzb.tools.network;

import GUIAlgorithm.Aouzb.tools.view.KBData;

import java.util.Iterator;

public interface WSSConnection {
    public boolean StartLink();
    public Iterator<Gamer> scoreList();
    public int getLinkedGamerNumber();
    public void closeLink();
    public KBData getData();
    public void setData(KBData data);
    public boolean isReady();
    public boolean isReady(int readyMember);
    public void changeGamerDirection(int gamerID, Directions direction);
    public Directions getGamerDirection(int gamerID);
    public void changeGamerScore(int gamerID, ScorePower scorePower);
    public int getGamerScore(int gameID);
}

package GUIAlgorithm.Aouzb.tools.network;

public class Gamer {
    // 与其他玩家踩在同一方格上，分数+1
    private static final int MEETING_ADD_POWER = 1;
    // 踩中默认的方块，分数+2
    private static final int DEFAULT_ADD_POWER = 2;
    // 踩中其他玩家的方块，分数+3
    private static final int COLOURED_ADD_POWER = 3;
    // 踩中自己的方块，分数-1
    private static final int OWNER_SUB_POWER = -1;
    // 自己的方块被其他玩家踩中，分数-2
    private static final int COLOURED_SUB_POWER = -2;
    // 玩家穿越边界复活，分数-7
    private static final int CROSS_BORER_SUB_POWER = -7;

    private final int gamerID;
    private final String gamerName;
    private int score = 0;
    private int carpetLength = 0;
    private Directions direction;

    public Gamer(int gamerID, String gamerName) {
        this.gamerID = gamerID;
        this.gamerName = gamerName;
    }

    public int getGamerID() {
        return gamerID;
    }

    public String getGamerName() {
        return gamerName;
    }

    public int getScore() {
        return score;
    }

    public int getCarpetLength() {
        return carpetLength;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public void addScore(){
        this.score++;
    }

    public void changeScore(ScorePower scorePower){
        switch (scorePower){
            case MEETING_ADD -> score += MEETING_ADD_POWER;
            case DEFAULT_ADD -> score += DEFAULT_ADD_POWER;
            case COLOURED_ADD -> score += COLOURED_ADD_POWER;
            case OWNER_SUB -> score += OWNER_SUB_POWER;
            case COLOURED_SUB -> score += COLOURED_SUB_POWER;
            case CROSS_BORER_SUB -> score += CROSS_BORER_SUB_POWER;
        }
    }

    public void changeDirection(Directions direction){
        this.direction = direction;
    }

    public Directions getGamerDirection(){
        return direction;
    }

    public void subScore(){
        this.score--;
    }

    public void resetScore(){
        setScore(0);
    }

    public void addCarpet(){
        this.carpetLength++;
    }

    public void subCarpet(){
        this.carpetLength--;
    }

    public void resetCarpet(){
        this.carpetLength = 0;
    }

    public void setCarpetLength(int carpetLength) {
        this.carpetLength = carpetLength;
    }
}

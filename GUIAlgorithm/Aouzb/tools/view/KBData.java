package GUIAlgorithm.Aouzb.tools.view;

import GUIAlgorithm.Aouzb.tools.network.ScorePower;
import java.util.HashSet;
import java.util.Set;

public class KBData {
    public static final int blockSize = 20;
    public static final int jumpSize = 30;
    private GameStatus gameStatus;
    private int N, M;
    private int[] carpet;
    private int[] users;
    public Distance white ;
    public Distance black;
    public int whiteScore = 0;
    public int blackScore = 0;

    public KBData(int N, int M) {
        this.N = N;
        this.M = M;
        carpet = new int[N*M];
        users = new int[18];
        white = Enums.random(Distance.class);
        black = Enums.random(Distance.class);
        for (int i=0;i<N*M;i++){
            carpet[i] = -1;
//            if (Math.random() > 0.03){
//                carpet[i] = -1;
//            }else {
//                carpet[i] = (int) (Math.random() * 17 - 1);
//            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<18;i++){
            int rand;
            do {
                rand = (int)(Math.random()*(N*M));
            } while (set.contains(rand) || inEdge(rand));
//            System.out.println(N>rand/M && M>rand%M);
            users[i] = rand;
            set.add(rand);
        }
    }

    private boolean inEdge(int num){
        return num/M == 0 || num/M == N - 1 || num % M == 0 || num % M == M - 1;
    }

    public KBData(int N, int M, GameStatus gameStatus){
        this(N, M);
        this.gameStatus = gameStatus;
    }

    public ScorePower judgeUserStatus(int userID){
        // TODO 判断并返回当前用户状态
        return ScorePower.DEFAULT_ADD;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public int[] getCarpet() {
        return carpet;
    }

    public int[] getUsers() {
        return users;
    }

    public void moveRandDistance(){
        for (int i=0;i<16;i++){
            Distance distance = Enums.random(Distance.class);
            switch (distance){
                case JumpLeft-> users[i]  = jumpToDistance(users[i] - M);
                case JumpRight -> users[i] = jumpToDistance(users[i] + M);
                case JumpUp -> users[i] = jumpToDistance(users[i] - 1);
                case JumpDown -> users[i] = jumpToDistance(users[i] + 1);
            }
            if (carpet[users[i]] == 16){
                whiteScore -= 2;
            }else if (carpet[users[i]] == 17){
                blackScore -=2;
            }
            carpet[users[i]] = i;
        }
        switch (white){
            case JumpLeft-> users[16]  = jumpToDistance(users[16] - M, true);
            case JumpRight -> users[16] = jumpToDistance(users[16] + M, true);
            case JumpUp -> users[16] = jumpToDistance(users[16] - 1, true);
            case JumpDown -> users[16] = jumpToDistance(users[16] + 1, true);
        }
        if (carpet[users[16]] == -1){
            whiteScore += 2;
        }else if (carpet[users[16]] == 16){
            whiteScore--;
        }else if (carpet[users[16]] == 17){
            whiteScore += 3;
            blackScore -= 2;
        }else{
            whiteScore += 3;
        }
        carpet[users[16]] = 16;
        switch (black){
            case JumpLeft-> users[17]  = jumpToDistance(users[17] - M, true);
            case JumpRight -> users[17] = jumpToDistance(users[17] + M, true);
            case JumpUp -> users[17] = jumpToDistance(users[17] - 1, true);
            case JumpDown -> users[17] = jumpToDistance(users[17] + 1, true);
        }
        if (carpet[users[17]] == -1){
            blackScore += 2;
        }else if (carpet[users[17]] == 17){
            blackScore--;
        }else if (carpet[users[17]] == 16){
            blackScore += 3;
            whiteScore -= 2;
        }else{
            blackScore += 3;
        }
        carpet[users[17]] = 17;
        for (int i=0;i<16;i++){
            if (users[16] == users[i]){
                whiteScore++;
                continue;
            }
            if (users[17] == users[i]){
                blackScore++;
                continue;
            }
            if (users[16] == users[17]){
                whiteScore++;
                blackScore++;
            }
        }
    }

    private int jumpToDistance(int jump){
        if (jump < 0){
            return M * N + jump;
        }
        if (jump >= M * N){
            return jump - M * N;
        }
        return jump;
    }

    private int jumpToDistance(int jump, boolean gamer){
        if (jump < 0){
            if (gamer){
                whiteScore -= 7;
            }else{
                blackScore -= 7;
            }
            return M * N + jump;
        }
        if (jump >= M * N){
            if (gamer){
                whiteScore -= 7;
            }else{
                blackScore -= 7;
            }
            return jump - M * N;
        }
        return jump;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}

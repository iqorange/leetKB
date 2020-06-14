package GUIAlgorithm.Aouzb.tools.network;

public enum ScorePower {
    // 与其他玩家踩在同一方块上
    MEETING_ADD,
    // 玩家发现了未探索区域！
    DEFAULT_ADD,
    // 玩家踩中了其他玩家的方块
    COLOURED_ADD,
    // 玩家踩在了自己的方块上
    OWNER_SUB,
    // 玩家已发现的方块被覆盖了
    COLOURED_SUB,
    // 复活！玩家穿墙而过～
    CROSS_BORER_SUB
}

package leetPack.DesignPatternsPractice.sol5;

public class DBClient {
    private static DBClient dbClient = new DBClient();

    public DBClient(){
        System.out.println("DB连接了");
    }

    public DBClient getDbClient(){
        return dbClient;
    }
}

package DesignPatternsPractice.sol5;

public class Main {
    public static void main(String[] args) {
        DBPool dbPool = new DBPool();
        DBClient dbClient1 = dbPool.getClient();
        System.out.println(dbClient1.hashCode());
        DBClient dbClient2 = dbPool.getClient();
        System.out.println(dbClient2.hashCode());
        dbPool.closeClient(dbClient1);
        DBClient dbClient3 = dbPool.getClient();
        System.out.println(dbClient3.hashCode());
    }
}

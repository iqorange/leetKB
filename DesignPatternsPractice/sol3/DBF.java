package DesignPatternsPractice.sol3;

public interface DBF {
    public Connection createConn();
    public Statement createState();
}

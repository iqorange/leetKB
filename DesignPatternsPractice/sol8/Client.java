package DesignPatternsPractice.sol8;

public class Client {
    public static void main(String[] args) {
        Adapter adapter = new Adapter(new Encrypt());
        adapter.toEncrypt();
    }
}

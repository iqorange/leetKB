package DesignPatternsPractice.sol14;

public class Client {
    public static void main(String[] args) {
        Context context = new Context(EncryptType.Caesar);
        context.algorithm();
    }
}

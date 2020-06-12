package leetPack.DesignPatternsPractice.sol12;

public class Client {
    public static void main(String[] args) {
        Subject cat = new Cat();
        Observer master = new Master();
        Observer mouse = new Mouse();
        cat.attach(master);
        cat.attach(mouse);
        cat.notify("叫了一声～");
    }
}

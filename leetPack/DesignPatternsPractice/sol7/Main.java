package leetPack.DesignPatternsPractice.sol7;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Adapter(new DogImpl());
        cat.sayVariety();
        cat.catchMouse();
        Dog dog = new Adapter(new CatImpl());
        dog.sayVariety();
        dog.wangwang();
    }
}

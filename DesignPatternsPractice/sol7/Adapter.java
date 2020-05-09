package DesignPatternsPractice.sol7;

public class Adapter implements Cat, Dog{
    private Cat cat;
    private Dog dog;


    public Adapter(Cat cat){
        this.cat = cat;
    }

    public Adapter(Dog dog){
        this.dog = dog;
    }


    @Override
    public void sayVariety() {
        if (cat == null){
            dog.sayVariety();
        }else{
            cat.sayVariety();
        }
    }

    @Override
    public void wangwang() {
        cat.catchMouse();
    }

    @Override
    public void catchMouse() {
        dog.wangwang();
    }
}

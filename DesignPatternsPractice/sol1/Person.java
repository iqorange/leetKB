package DesignPatternsPractice.sol1;

public abstract class Person {
    private String name;
    private int age;
    public void say(){
        System.out.println("大家好，我叫" + this.name + "，今年" + this.age + "岁了。");
    }
}

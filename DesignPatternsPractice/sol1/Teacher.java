package DesignPatternsPractice.sol1;

public class Teacher extends Person {
    private String name;
    private int age;
    private int workYears;

    public Teacher() {
    }

    public Teacher(String name, int age, int workYears) {
        this.name = name;
        this.age = age;
        this.workYears = workYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWorkYears() {
        return workYears;
    }

    public void setWorkYears(int workYears) {
        this.workYears = workYears;
    }

    @Override
    public void say() {
        System.out.println("大家好，我叫" + this.name + "，我已经工作了。");
    }

    // 大家好,我叫XX,我今年XX岁了,我已经工作XX年了
    public void sayHello(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("大家好，我叫");
        stringBuilder.append(this.name);
        stringBuilder.append("，我今年");
        stringBuilder.append(this.age);
        stringBuilder.append("岁了，我已经工作");
        stringBuilder.append(this.workYears);
        stringBuilder.append("年了。");
        System.out.println(stringBuilder.toString());
    }
}

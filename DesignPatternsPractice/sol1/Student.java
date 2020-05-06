package sol1;

public class Student extends Person {
    // 姓名、性别、年龄、语文、数学、英语
    private String name;
    private String sex;
    private int age;
    private int chineseRecord;
    private int mathRecord;
    private int englishRecord;

    public Student() {
    }

    public Student(String name, String sex, int age, int chineseRecord, int mathRecord, int englishRecord) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.chineseRecord = Math.max(0, chineseRecord);
        this.mathRecord = Math.max(0, mathRecord);
        this.englishRecord = Math.max(0, mathRecord);
    }

    public Student(String name, String sex) {
        this.name = name;
        this.sex = sex;
        this.age = 18;
        this.chineseRecord = 0;
        this.mathRecord = 0;
        this.englishRecord = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getChineseRecord() {
        return chineseRecord;
    }

    public void setChineseRecord(int chineseRecord) {
        this.chineseRecord = chineseRecord;
    }

    public int getMathRecord() {
        return mathRecord;
    }

    public void setMathRecord(int mathRecord) {
        this.mathRecord = mathRecord;
    }

    public int getEnglishRecord() {
        return englishRecord;
    }

    public void setEnglishRecord(int englishRecord) {
        this.englishRecord = englishRecord;
    }

    @Override
    public void say() {
        System.out.println("大家好，我叫" + this.name + "，今年" + this.age + "岁了。");
    }

    public void sayHello(){
        System.out.println("我叫" + this.name + "，今年" + this.age + "岁了，是" + this.sex + "同学。");
    }

    // 我叫XX,这次考试总成绩为X分,平均成绩为X分
    public void myAverageRecord(){
        System.out.println("我叫" + this.name + "，这次考试总成绩为" + sumRecord() + "分，平均成绩为" + avgRecord() + "分。");
    }

    private int sumRecord(){
        return chineseRecord + mathRecord + englishRecord;
    }

    private float avgRecord(){
        return (float) sumRecord()/3;
    }
}


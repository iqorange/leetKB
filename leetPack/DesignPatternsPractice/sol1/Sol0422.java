package leetPack.DesignPatternsPractice.sol1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Sol0422 {
    Student student1;
    Student student2;
    Ticket ticket;
    Teacher teacher;
    @Before
    public void before(){
        // 张三 男 18  三科成绩为:90 95 80
        // 小兰 女 16  三科成绩为:95 85 100
        student1 = new Student("张三", "男", 18, 90, 95, 80);
        student2 = new Student("小兰", "女", 16, 95, 85, 100);
        ticket = new Ticket(280);
        teacher = new Teacher("Judy", 19, 5);
    }

    @Test
    public void test1(){
        student1.sayHello();
        student2.sayHello();
        student1.myAverageRecord();
        student2.myAverageRecord();
    }

    @Test
    public void test2(){
        System.out.println(ticket.getPrice());
    }

    @Test
    public void test3(){
        student1.sayHello();
        teacher.sayHello();
    }

    @Test
    public void test4(){
        student1.say();
        teacher.say();
    }

    @After
    public void after(){
        student1 = null;
        student2 = null;
        ticket = null;
        teacher = null;
    }
}

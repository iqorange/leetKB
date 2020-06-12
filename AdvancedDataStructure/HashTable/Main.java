package AdvancedDataStructure.HashTable;

public class Main {
    public static void main(String[] args) {
        int a = 65;
        System.out.println(Integer.hashCode(a));
        int b = -65;
        System.out.println(Integer.hashCode(b));
        double c = 3.1415926;
        System.out.println(Double.hashCode(c));
        String d = "Hello!";
        System.out.println(d.hashCode());
        Student student = new Student(2,1,"kb");
        System.out.println(student.hashCode());
    }
}

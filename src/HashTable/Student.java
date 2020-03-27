package HashTable;

public class Student {
    int grade;
    int cls;
    String name;

    public Student(int grade, int cls, String name) {
        this.grade = grade;
        this.cls = cls;
        this.name = name;
    }

    @Override
    public int hashCode(){
        int B = 31;
        int hash = 0;
        // 溢出循环
        hash = hash * B +grade;
        hash = hash * B + cls;
        hash = hash * B + name.toLowerCase().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null){
            return false;
        }
        if (getClass() != o.getClass()){
            return false;
        }
        Student another = (Student)o;
        return this.grade == another.grade && this.cls == another.cls && this.name.toLowerCase().equals(another.name.toLowerCase());
    }
}

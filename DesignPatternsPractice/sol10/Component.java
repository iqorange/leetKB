package DesignPatternsPractice.sol10;

public abstract class Component {
    private String departname;

    public Component(String departname){
        this.departname = departname;
    }

    public abstract void add(Component c);
    public abstract void remove(Component c);
    public abstract Component getChild(int i);
    public abstract void operation();
    public abstract void sendPost(String superDepart);

    public String getDepartname() {
        return departname;
    }
}

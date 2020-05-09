package DesignPatternsPractice.sol10;

public class Client {
    public static void main(String[] args) {
        Component component = new Composite("北京总部");
        component.add(new Leaf("教务办公室"));
        component.add(new Leaf("行政办公室"));
        Component c1 = new Composite("湖南分校");
        component.add(c1);
        c1.add(new Leaf("教务办公室"));
        c1.add(new Leaf("行政办公室"));
        Component c2 = new Composite("长沙教学点");
        Component c3 = new Composite("湘潭教学点");
        c1.add(c2);
        c1.add(c3);
        c2.add(new Leaf("教务办公室"));
        c2.add(new Leaf("行政办公室"));
        c3.add(new Leaf("教务办公室"));
        c3.add(new Leaf("行政办公室"));
        c1.sendPost("");
    }
}

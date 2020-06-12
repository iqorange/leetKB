package leetPack.DesignPatternsPractice.sol10;

public class Leaf extends Component {
    public Leaf(String departname) {
        super(departname);
    }

    @Override
    public void add(Component c) {
        throw new IllegalArgumentException(getDepartname() + "is a Leaf!");
    }

    @Override
    public void remove(Component c) {
        throw new IllegalArgumentException(getDepartname() + "is a Leaf!");
    }

    @Override
    public Component getChild(int i) {
        return null;
    }

    @Override
    public void operation() {
        System.out.println(getDepartname() + "收到公文！");
    }

    @Override
    public void sendPost(String superDepart) {
        System.out.print(superDepart + "的");
        operation();
    }
}

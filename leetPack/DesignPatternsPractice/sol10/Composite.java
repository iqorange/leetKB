package leetPack.DesignPatternsPractice.sol10;

import java.util.ArrayList;

public class Composite extends Component{
    private ArrayList<Component> list = new ArrayList<>();

    public Composite(String departname) {
        super(departname);
    }

    @Override
    public void add(Component c) {
        list.add(c);
    }

    @Override
    public void remove(Component c) {
        list.remove(c);
    }

    @Override
    public Component getChild(int i) {
        return list.get(i);
    }

    @Override
    public void operation() {
        for (Component component: list){
            component.operation();
        }
    }

    // 下发公文
    @Override
    public void sendPost(String superDepart){
        System.out.println("--- "+ getDepartname() +" ---");
        for (Component c: list){
            c.sendPost(getDepartname());
        }
    }
}

package sol4;

import javax.swing.*;

public class JInternalFrame1 extends JInternalFrame {
    private static JInternalFrame1 jInternalFrame1 = null;
    private JInternalFrame1(String name, boolean b1, boolean b2, boolean b3, boolean b4){
        super(name, b1, b2, b3, b4);
    }

    public static JInternalFrame1 getjInternalFrame1(String name, boolean b1, boolean b2, boolean b3, boolean b4){
        if (jInternalFrame1 == null){
            jInternalFrame1 = new JInternalFrame1(name, b1, b2, b3, b4);
        }
        return jInternalFrame1;
    }
}

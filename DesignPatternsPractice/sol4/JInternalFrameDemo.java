package sol4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JInternalFrameDemo extends JFrame implements ActionListener {
    private static JInternalFrame1 internalFrame1;
    Container container = this.getContentPane();
    public JInternalFrameDemo(){
        super("主窗体");
        container.setLayout(new BorderLayout());
        JButton button = new JButton("创建一个窗体");
        button.addActionListener(this);
        container.add(button, BorderLayout.SOUTH);
        this.setSize(new Dimension(300, 300));
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e){
        internalFrame1 = JInternalFrame1.getjInternalFrame1("子窗体", true, true, true, true);
        internalFrame1.setSize(new Dimension(200, 200));
        internalFrame1.setVisible(true);
        JDesktopPane desktopPane = new JDesktopPane();
        container.add(desktopPane);
        desktopPane.add(internalFrame1);
        try {
            internalFrame1.setSelected(true);
        }catch (java.beans.PropertyVetoException ex){
            System.out.println("Exception while selecting");
        }
    }
}

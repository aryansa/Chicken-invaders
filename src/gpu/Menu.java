package gpu;

import javax.swing.*;

class Menu extends JMenuBar {
    Menu() {
        JMenu meno = new JMenu("Menu");
        this.add(meno);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        meno.add(exit);
    }
}
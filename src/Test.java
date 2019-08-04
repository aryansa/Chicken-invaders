import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
/*  w  w w . j  a v  a 2 s .  c om*/
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JFrame {
    private JPanel childPanel1 = new JPanel();
    private JPanel childPanel2 = new JPanel();
    private JPanel childPanel3 = new JPanel();

    public Test() {
        childPanel1.setBackground(Color.red);
        childPanel1.setPreferredSize(new Dimension(300, 40));
        childPanel2.setBackground(Color.blue);
        childPanel2.setPreferredSize(new Dimension(300, 40));
        childPanel3.setBackground(Color.yellow);
        childPanel3.setPreferredSize(new Dimension(300, 40));

        JButton myButton = new JButton("Add Component ");
        myButton.addActionListener(e -> {
            add(childPanel2, BorderLayout.CENTER);
            pack();
        });
        setLocation(10, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(childPanel3, BorderLayout.CENTER);
        add(myButton, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        Test Main = new Test();
    }
}
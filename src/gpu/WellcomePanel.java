package gpu;

import root.Main;
import root.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WellcomePanel extends JPanel {
    Image backgroundimage;

    public WellcomePanel() {
        this.setBounds(0, 0, Settings.screenSize.width, Settings.screenSize.height);
        backgroundimage = Toolkit.getDefaultToolkit().createImage(Settings.backgroundImage).getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
      JButton jButton = new JButton("salam");
      jButton.setBounds(100,100,200,200);
      jButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) {
              Main.frame.setJpanel(new TestFrame());
          }
      });
this.add(jButton);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backgroundimage,
                0, 0, new Color(0, 0, 0, 0), null);
        repaint();
    }
}

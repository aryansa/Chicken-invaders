package gpu;

import root.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static root.Main.mySqlService;

public class WellcomePanel extends JPanel {
    private Image backgroundImage;
    int i = 0;

    WellcomePanel() {
        this.setLayout(null);
        this.setBounds(0, 0, Settings.screenSize.width, Settings.screenSize.height);
        backgroundImage = Toolkit.getDefaultToolkit().createImage(Settings.backgroundImage).getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
        int buttonY = getHeight() - (getHeight() / 4);
        int startPoint = getWidth() / 4;
        int buttonWidth = getWidth() / 8;
        int buttonHeight = getHeight() / 16;
        int buttonRaduis = 20;
        JButton newUser = new RoundedBorderButton("New User", buttonRaduis);
        newUser.setBounds(startPoint, buttonY, buttonWidth, buttonHeight);
        JButton run = new RoundedBorderButton("Let's Go", buttonRaduis);
        run.setBounds((getWidth() / 2) - (buttonWidth / 2), buttonY, buttonWidth, buttonHeight);
        JButton delete = new RoundedBorderButton("Delete User ", buttonRaduis);
        delete.setBounds((getWidth() * 3 / 4) - buttonWidth, buttonY, buttonWidth, buttonHeight);
//        newUser.setEnabled(false);
//        delete.setEnabled(false);
//        run.setEnabled(false);
        this.add(run);
        this.add(delete);
        this.add(newUser);
        newUser.addActionListener(actionEvent -> {
            String name = JOptionPane.showInputDialog("Set Save Name :");
            if (name.length() > 1) {
                try {
                    String[] head = new String[1];
                    head[0] = "name";
                    String[] value = new String[1];
                    value[0] = name;
                    mySqlService.insert("users", head, value);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            updateInnerPanel();
        });
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(null);
        innerPanel.setBounds(startPoint, getHeight() / 8, getWidth() / 2, getHeight() / 2);
        innerPanel.setBackground(new Color(255, 255, 255, 50));
        innerPanel.setVisible(true);

        JLabel name = new JLabel("<html><i style='color:white;font-size:15px'>Name</i></html>");
        name.setBounds(10, 10, innerPanel.getWidth() / 2, innerPanel.getHeight() / 10);
        name.setVisible(true);
        JLabel score = new JLabel("<html><i style='color:white;font-size:15px'>Score</i></html>");
        score.setBounds(innerPanel.getWidth() / 2, 10, (innerPanel.getWidth() * 3 / 4) + 10, innerPanel.getHeight() / 10);
        score.setVisible(true);
        innerPanel.add(name);
        innerPanel.add(score);
        this.add(innerPanel);
        this.setVisible(true);
    }

    private void updateInnerPanel() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backgroundImage,
                0, 0, new Color(0, 0, 0, 0), null);
        repaint();
    }
}

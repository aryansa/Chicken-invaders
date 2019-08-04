package gpu;

import Containers.User;
import root.Main;
import root.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserPanel extends MyPanel {
    User user;

    public UserPanel(User user) {
        this.user = user;
        int buttonY = getHeight() - (getHeight() / 4);
        int startPoint = getWidth() / 4;
        int buttonWidth = getWidth() / 8;
        int buttonHeight = getHeight() / 16;
        int buttonRaduis = 20;
        this.setBackgroundImage(Settings.USER_PANEL_JPG);
        JLabel jLabel = new JLabel("<html><i style='font-size:15px;color:white'>Hello "+user.getName()+" \n Space is waiting for you !</i></html>");
        jLabel.setBounds((this.getWidth()/2)-100,getHeight()/32,200,getHeight()/16);
        JButton run = new RoundedBorderButton("New Game", buttonRaduis);
        run.setBounds(startPoint, buttonY, buttonWidth, buttonHeight);
        JButton load = new RoundedBorderButton("Load Game", buttonRaduis);
        load.setBounds((getWidth() / 2) - (buttonWidth / 2), buttonY, buttonWidth, buttonHeight);
        JButton back = new RoundedBorderButton("Back", buttonRaduis);
        back.setBounds((getWidth() * 3 / 4) - buttonWidth, buttonY, buttonWidth, buttonHeight);
        back.addActionListener(actionEvent -> {
            try {
                Main.frame.setJpanel(new WellcomePanel());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        jLabel.setVisible(true);
        this.add(jLabel);
        this.add(run);
        this.add(load);
        this.add(back);
        this.setVisible(true);
    }
}
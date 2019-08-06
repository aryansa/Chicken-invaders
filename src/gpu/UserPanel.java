package gpu;

import containers.Game;
import containers.Spaceship;
import containers.User;
import root.Main;
import root.Settings;

import javax.swing.*;
import java.sql.SQLException;

class UserPanel extends MyPanel {
    User user;

    UserPanel(User user) {
        this.user = user;
        int buttonY = getHeight() - (getHeight() / 4);
        int startPoint = getWidth() / 4;
        int buttonWidth = getWidth() / 8;
        int buttonHeight = getHeight() / 16;
        int buttonRadius = 20;
        this.setBackgroundImage(Settings.USER_PANEL_JPG);
        JLabel jLabel = new JLabel("<html><i style='font-size:15px;color:white'>Hello " + user.getName() + " \n Space is waiting for you !</i></html>");
        jLabel.setBounds((this.getWidth() / 2) - 100, getHeight() / 32, 200, getHeight() / 16);
        JButton runing = new RoundedBorderButton("New Game", buttonRadius);
        runing.setBounds(startPoint, buttonY, buttonWidth, buttonHeight);
        JButton load = new RoundedBorderButton("Load Game", buttonRadius);
        load.setBounds((getWidth() / 2) - (buttonWidth / 2), buttonY, buttonWidth, buttonHeight);
        JButton back = new RoundedBorderButton("Back", buttonRadius);
        back.setBounds((getWidth() * 3 / 4) - buttonWidth, buttonY, buttonWidth, buttonHeight);
        back.addActionListener(actionEvent -> {
            try {
                Main.frame.setJpanel(new WellcomePanel());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        runing.addActionListener(actionEvent -> {
            Game game = new Game(user);
            game.setSpaceship(new Spaceship());
            Main.frame.setJpanel(new GamePanel(game));
        });
        jLabel.setVisible(true);
        this.add(jLabel);
        this.add(runing);
        this.add(load);
        this.add(back);
        this.setVisible(true);
    }
}
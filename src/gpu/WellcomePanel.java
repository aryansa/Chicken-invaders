package gpu;

import Containers.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static root.Main.frame;
import static root.Main.mySqlService;

class WellcomePanel extends MyPanel {
    private JComboBox<Object> comboBox = new JComboBox<>();

    WellcomePanel() throws SQLException {
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
            try {
                updateInnerPanel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        delete.addActionListener(actionEvent -> {
            User user = (User) comboBox.getModel().getSelectedItem();
            try {
                mySqlService.delete("users", user.getId());
                updateInnerPanel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                User user = (User) comboBox.getModel().getSelectedItem();
                frame.setJpanel(new UserPanel(user));
            }
        });
        JPanel innerPanel = new JPanel();
        innerPanel.setBounds(startPoint, getHeight() / 8, getWidth() / 2, getHeight() / 2);
        innerPanel.setBackground(new Color(255, 255, 255, 50));
        innerPanel.setLayout(null);

        JLabel name = new JLabel("<html><i style='color:white;font-size:15px'>Select Game : </i></html>");
        name.setBounds(10, 10, innerPanel.getWidth() / 2, innerPanel.getHeight() / 10);
        name.setVisible(true);
        innerPanel.add(name);
        comboBox.setBounds(15, name.getY() + name.getHeight(), innerPanel.getWidth() - 30, 100);
        updateInnerPanel();
        innerPanel.add(comboBox);
        innerPanel.setVisible(true);
        this.add(innerPanel);
        this.setVisible(true);
    }

    private void updateInnerPanel() throws SQLException {
        ResultSet users = null;
        try {
            users = mySqlService.selectAll("users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        comboBox.removeAllItems();
        comboBox.addItem("Select ... ");
        while (users.next()) {
            String id = users.getString("id");
            String name = users.getString("name");
            String score = users.getString("score");
            User user = new User(Integer.parseInt(id), name, Integer.parseInt(score));
            comboBox.addItem(user);
        }
    }

}

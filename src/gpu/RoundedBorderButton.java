package gpu;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

 class RoundedBorderButton extends JButton {
     RoundedBorderButton(String label, int radius) {
        super("<html><i style='color:white;font-size:15px'>" + label + "</i><html>");
        setBorder(new RoundedBorder(radius));
        setForeground(Color.BLUE);
        setContentAreaFilled(false);
        addMouseListener(new mListener(this));
    }
}

class mListener implements MouseListener {
    private JButton jButton;

    mListener(JButton jButton) {
        this.jButton = jButton;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        jButton.setForeground(Color.red);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        jButton.setForeground(Color.BLUE);
    }
}


class RoundedBorder implements Border {

    private int radius;


    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}

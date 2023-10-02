import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class Mouse extends JFrame implements MouseInputListener {
    
    Container cp = getContentPane();

    int currIndex = 0;

    int diffX = 0, diffY = 0;
    boolean isDragged;
    
    Border  normal = BorderFactory.createLineBorder(Color.black),
            pointed = BorderFactory.createLineBorder(Color.red, 2),
            dragged = BorderFactory.createLineBorder(Color.blue, 4);

    public Mouse() {
        cp.setLayout(null);

        cp.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.isMetaDown()) {
                    if (e.isControlDown()) {
                        removeAllComponents();
                    } else {
                        setVisibility();
                    }
                } else {
                    addLabel(e.getX(), e.getY());
                }
            }
        });

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        show();
    }

    private void addLabel(int x, int y) {
        JLabel l = new JLabel("" + (char) ('A' + currIndex++));
        l.setBounds(x, y, 50, 50);
        l.setBorder(normal);
        l.setFont(new Font("Dialog", Font.BOLD, 24));
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.CENTER);
        l.addMouseListener(this);
        l.addMouseMotionListener(this);
        cp.add(l);
        cp.repaint();
    }

    private void setVisibility() {
        Component[] c = cp.getComponents();
        for (int i = 0; i < c.length; i++) {
            c[i].setVisible(!c[i].isVisible());
        }
    }

    private void removeAllComponents() {
        cp.removeAll();
        cp.repaint();
    }

    public void mouseEntered(MouseEvent e) {
        if (!isDragged) ((JComponent) e.getSource()).setBorder(pointed);
    }

    public void mouseExited(MouseEvent e) {
        if (!isDragged) ((JComponent) e.getSource()).setBorder(normal);
    }

    public void mousePressed(MouseEvent e) {
        isDragged = true;
        ((JComponent) e.getSource()).setBorder(dragged);
        diffX = e.getX();
        diffY = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
        isDragged = false;
        if (e.isControlDown()) {
            cp.remove(e.getComponent());
            cp.repaint();
            return;
        }
        ((JComponent) e.getSource()).setBorder(pointed);
    }

    public void mouseDragged(MouseEvent e) {
        Component c = e.getComponent();
        c.setLocation(c.getX() + e.getX() - diffX, c.getY() + e.getY() - diffY);
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    public static void main(String[] args) {
        new Mouse();
    }
}

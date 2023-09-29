import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Warstwy extends JFrame implements MouseListener {
    
    JLayeredPane lp;

    public static void main(String[] args) {
        new Warstwy();
    }

    public Warstwy() {
        setTitle("Ilustracja warstw okna");
        JButton b1,b2,b3,b4,b5,b6;
        int x = 10, y = 10, dx = 30, dy = 30;
        int w = 150, h = 150;

        lp = getLayeredPane();
        Container cp = getContentPane();
        cp.setLayout(null);

        b1 = new JButton("Frame Layer");
        b1.setVerticalAlignment(JButton.TOP);
        b1.setBounds(x, y, w, h);
        b1.addMouseListener(this);
        cp.add(b1);

        b2 = new JButton("Default Layer");
        b2.setVerticalAlignment(JButton.TOP);
        b2.setBounds(x += dx, y += dy, w, h);
        b2.addMouseListener(this);
        lp.add(b2, JLayeredPane.DEFAULT_LAYER);

        b3 = new JButton("Palette Layer");
        b3.setVerticalAlignment(JButton.TOP);
        b3.setBounds(x += dx, y += dy, w, h);
        b3.addMouseListener(this);
        lp.add(b3, JLayeredPane.PALETTE_LAYER);

        b4 = new JButton("Modal Layer");
        b4.setVerticalAlignment(JButton.TOP);
        b4.setBounds(x += dx, y += dy, w, h);
        b4.addMouseListener(this);
        lp.add(b4, JLayeredPane.MODAL_LAYER);

        b5 = new JButton("Popup Layer");
        b5.setVerticalAlignment(JButton.TOP);
        b5.setBounds(x += dx, y += dy, w, h);
        b5.addMouseListener(this);
        lp.add(b5, JLayeredPane.POPUP_LAYER);

        b6 = new JButton("Drag Layer");
        b6.setVerticalAlignment(JButton.TOP);
        b6.setBounds(x += dx, y += dy, w, h);
        b6.addMouseListener(this);
        lp.add(b6, JLayeredPane.DRAG_LAYER);

        setSize(400, 400);
        setResizable(false);
        setVisible(true);
    }

    public void mousePressed(MouseEvent evt) {
        JButton source = (JButton) evt.getSource();

        if (evt.isMetaDown()) {
            source.setText("Default Layer");
            lp.setLayer(source, 0);
        }
        else {
            source.setText("Drag Layer");
            lp.setLayer(source, 400);
        }
    }

    public void mouseReleased(MouseEvent evt){}
    public void mouseEntered(MouseEvent evt){}
    public void mouseExited(MouseEvent evt){}
    public void mouseClicked(MouseEvent evt){}
}

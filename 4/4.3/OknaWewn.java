import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OknaWewn implements ActionListener, WindowConstants {
    
    JFrame f = new JFrame("Desktop");
    Component glass = f.getRootPane().getGlassPane();
    JLayeredPane lc = new JDesktopPane();

    ImageIcon toFront = new ImageIcon("up24.gif");
    ImageIcon toBack = new ImageIcon("down24.gif");
    ImageIcon stopIcon = new ImageIcon("world24.gif");

    Color[] color = { Color.blue, Color.green, Color.yellow, Color.gray };
    final int maxc = color.length;

    public static void main(String[] args) {
        new OknaWewn();
    }

    public OknaWewn() {

        Container cp = f.getContentPane();

        for (int i = 0; i < maxc; i++) {
            JInternalFrame w =
                new JInternalFrame("Okienko " + i, true, true, true, true);
            Container wcp = w.getContentPane();
            wcp.setLayout(new BorderLayout(5, 5));

            JPanel controls = new JPanel();
            controls.setBorder(BorderFactory.createRaisedBevelBorder());

            JButton b;
            b = new JButton("To front", toFront);
            b.addActionListener(this);

            controls.add(b);

            b = new JButton("To back", toBack);
            b.addActionListener(this);

            controls.add(b);

            b = new JButton(stopIcon);
            b.addActionListener(this);

            b.setBackground(color[i]);
            controls.add(b);

            wcp.add(controls, "North");

            b = new JButton("<html><center><b><font color=red>Koniec</font><br><font color=blue>albo początek</font><br><b>zabawy</b</center></html>");
            b.addActionListener(this);

            wcp.add(b, "Center");
            w.pack();

            lc.add(w, new Integer(i));
            w.setVisible(true);
        }
        f.getRootPane().setLayeredPane(lc);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.setSize(600, 600);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        JButton c = (JButton) e.getSource();
        JRootPane rp = c.getRootPane();
        JInternalFrame w = (JInternalFrame) rp.getParent();

        final String cmd = e.getActionCommand();

        if (cmd.equals("To front")) {
            lc.remove(w);
            lc.add(w, new Integer(maxc));
            w.toFront();
        } else if (cmd.equals("To back")) {
            lc.remove(w);
            lc.add(w, new Integer(-1));
            w.toBack();
        } else {
            glass.setVisible(true);
            f.setTitle("Next mouse press will draw red oval");
            glass.addMouseListener( new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    Graphics glassGraphics = glass.getGraphics();
                    glassGraphics.setColor(Color.red);
                    glassGraphics.fillOval(e.getX() - 25, e.getY() - 25, 50, 50);
                }
            });
        }
    }
}

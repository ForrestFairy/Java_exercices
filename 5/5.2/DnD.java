import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class DnD extends JFrame{
    
    private final int dim = 4;
    private final int fldSize = 50;
    private final int iconSize = 32;

    private final Icon blueIcon = new OvalIcon(Color.blue, iconSize);
    private final Icon cyanIcon = new OvalIcon(Color.cyan, iconSize);

    private final Color black = Color.black;
    private final Color white = Color.white;

    private Container cp = getContentPane();
    private JLayeredPane layers = getLayeredPane();

    private JPanel chessPane = new JPanel();

    private MouseWatcher mWatcher = new MouseWatcher();

    public DnD() {
        super("Drag'n'Drop");
        setLocation(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp.setLayout(new BorderLayout());

        fillBoard();

        pack();
        show();
    }

    private void fillBoard() {
        short fldPos = 0;
        cp.add(chessPane, BorderLayout.CENTER);
        chessPane.setLayout(new GridLayout(dim, dim));
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Color bgColor = (fldPos % 2 == 0 ? white : black);
                JLabel field = new JLabel();
                if (i == j) {
                    Icon icon = (i % 2 == 0 ? cyanIcon : blueIcon);
                    field.setIcon(icon);
                }

                field.setHorizontalAlignment(JLabel.CENTER);
                field.setPreferredSize(new Dimension(fldSize, fldSize));
                field.setBackground(bgColor);
                field.setOpaque(true);
                field.addMouseListener(mWatcher);
                field.addMouseMotionListener(mWatcher);

                chessPane.add(field);
                fldPos++;
            }
            fldPos++;
        }
    }

    class MouseWatcher implements MouseInputListener {

        private boolean dragging = false;
        private JLabel dragged;
        private int lx, ly;
        private int ex, ey;

        MouseWatcher() {
            dragged = new JLabel();
            dragged.setHorizontalAlignment(JLabel.CENTER);
            dragged.setPreferredSize(new Dimension(fldSize, fldSize));

            dragged.setVisible(false);

            layers.add(dragged, JLayeredPane.DRAG_LAYER);
        }

        public void mousePressed(MouseEvent e) {
            if (e.getPoint().distance(new Point(fldSize / 2, fldSize / 2)) > iconSize / 2) {
                return;
            }
            JLabel label = (JLabel) e.getSource();
            Icon icon = label.getIcon();
            label.setIcon(null);

            ex = e.getX();
            ey = e.getY();
            lx = label.getX();
            ly = label.getY();

            dragged.setIcon(icon);
            dragged.setBounds(lx, ly, fldSize, fldSize);
            dragged.setVisible(true);
            dragging = true;
        }

        public void mouseReleased(MouseEvent e) {
            if (!dragging) {
                return;
            }
            dragging = false;
            JLabel src =  (JLabel) e.getSource();
            Point srcPos = src.getLocation();
            Point dstPos = e.getPoint();

            srcPos.translate(dstPos.x, dstPos.y);
            JLabel dst;

            Component c = chessPane.getComponentAt(srcPos);

            if (c instanceof JLabel) {
                dst = (JLabel) c;
            } else {
                dst = src;
            }

            if (dst.getBackground() == white && dst.getIcon() == null) {
                dst.setIcon(dragged.getIcon());
            } else {
                src.setIcon(dragged.getIcon());
            }
            dragged.setVisible(false);
        }

        public void mouseDragged(MouseEvent e) {
            dragged.setLocation(lx + e.getX() - ex, ly + e.getY() - ey);
        }

        public void mouseClicked(MouseEvent e) {}        
        public void mouseMoved(MouseEvent e) {}        
        public void mouseEntered(MouseEvent e) {}    
        public void mouseExited(MouseEvent e) {}    
    }

    public static void main(String[] args) {
        new DnD();
    }
}

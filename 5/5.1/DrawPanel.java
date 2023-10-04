import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;

import javax.swing.*;

public class DrawPanel extends JPanel {
    
    Graphics2D g;

    int w, h;

    private MouseHandler mouseHandler;
    private ActionHandler actionHandler;

    ArrayList database;
    ArrayList databaseAll;
    ArrayList data;
    int size;

    Point point;
    Dimension dim;
    Color color;

    Random rand;

    JRootPane root;

    JMenuBar menuBar;

    JMenu figures, clean, rebuild, dragg, help;
    JMenu DBactive;
    JMenu panelDBstate;
    JMenu screenDim;

    JMenuItem clearPanel, clearDB;
    JMenuItem restore, restoreAll, dragged, about;
    JMenuItem rectangle, circle, triangle;
    JMenuItem changePos, changeDim, changeColor, delete;

    JPopupMenu change;

    Figure figure;
    int x, y;
    int dx, dy;
    Object source;

    DrawPanel(int a, int b) {
        initComponents(a, b);
    }

    public void initComponents(int a, int b) {
        setBackground(Color.white);
        setPreferredSize(new Dimension(a, b));
        setBorder(BorderFactory.createLineBorder(Color.blue, 3));
        menuBar = new JMenuBar();

        figures = new JMenu("Figure");
        clean = new JMenu("Clean");
        rebuild = new JMenu("Rebuild");
        dragg = new JMenu("Dragg");
        help = new JMenu("Help");

        rectangle = new JMenuItem("Rectangle");
        circle = new JMenuItem("Circle");
        triangle = new JMenuItem("Triangle");

        clearPanel = new JMenuItem("ClearPanel");
        clearDB = new JMenuItem("ClearPanel-DB");

        restore = new JMenuItem("RestorePanel-DB");
        restoreAll = new JMenuItem("RestoreAll");

        dragged = new JCheckBoxMenuItem("Dragged");

        about = new JMenuItem("About");

        DBactive = new JMenu("Panel-DB active");
        panelDBstate = new JMenu("Panel-DB empty");
        screenDim = new JMenu("ScreenDim");

        figures.add(rectangle);
        figures.addSeparator();
        figures.add(circle);
        figures.addSeparator();
        figures.add(triangle);

        clean.add(clearPanel);
        clean.add(clearDB);

        rebuild.add(restore);
        rebuild.add(restoreAll);

        dragg.add(dragged);
        help.add(about);

        menuBar.add(figures);
        menuBar.add(clean);
        menuBar.add(rebuild);
        menuBar.add(dragg);
        menuBar.add(help);
        menuBar.add(new JSeparator(SwingConstants.VERTICAL));
        menuBar.add(DBactive);
        menuBar.add(new JSeparator(SwingConstants.VERTICAL));
        menuBar.add(panelDBstate);
        menuBar.add(new JSeparator(SwingConstants.VERTICAL));
        menuBar.add(screenDim);

        actionHandler = new ActionHandler();

        rectangle.addActionListener(actionHandler);
        circle.addActionListener(actionHandler);
        triangle.addActionListener(actionHandler);

        clearPanel.addActionListener(actionHandler);
        clearDB.addActionListener(actionHandler);
        restore.addActionListener(actionHandler);
        restoreAll.addActionListener(actionHandler);
        dragged.addActionListener(actionHandler);
        about.addActionListener(actionHandler);

        database = new ArrayList();
        databaseAll = new ArrayList();
        data = database;

        change = new JPopupMenu("Change");

        changePos = new JMenuItem("ChangePos");
        changePos.addActionListener(actionHandler);
        change.add(changePos);
        
        changeDim = new JMenuItem("ChangeDim");
        changeDim.addActionListener(actionHandler);
        change.add(changeDim);

        changeColor = new JMenuItem("ChangeColor");
        changeColor.addActionListener(actionHandler);
        change.add(changeColor);

        delete = new JMenuItem("Delete");
        delete.addActionListener(actionHandler);
        change.add(delete);

        mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        // addMouseMotionListener(mouseHandler);

        rand = new Random();
    }

    public void initMenu() {
        root = getRootPane();
        root.setJMenuBar(menuBar);
    }

    public void paintComponent(Graphics gdc) {
        super.paintComponent(gdc);

        w = getWidth();
        h = getHeight();
        g = (Graphics2D) getGraphics();

        screenDim.setText("Panel dim: " + "w= " + w + " h= " + h);

        if (data != null) {
            gdc.clearRect(0, 0, w, h);
            size = data.size();
            for (int i = 0; i < size; i++) {
                figure = (Figure) data.get(i);
                figure.draw((Graphics2D)gdc);
            }
        }
    }

    class ActionHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (dragged.isSelected()) dragg.setBackground(Color.red);
            else dragg.setBackground(null);

            source = e.getSource();

            if (source == clearPanel) g.clearRect(3, 3, w - 6, h - 6);

            if(source == clearDB) {
                database.clear();
                panelDBstate.setText("Panel-DB empty");
            }
            if (source == restore) {
                data = database;
                DBactive.setText("Panel-DB active");
                if (data.size() == 0) panelDBstate.setText("Panel-DB empty");
                else panelDBstate.setText("Panel-DB filled");
                repaint();
            }
            if (source == restoreAll) {
                data = databaseAll;
                DBactive.setText("All-DB active");
                if (data.size() == 0) panelDBstate.setText("All-DB empty");
                else panelDBstate.setText("All-DB filled");
                repaint();
            }
            if (source == changePos) {
                changeCall("point");
            }
            if (source == changeDim) {
                changeCall("dim");
            }
            if (source == changeColor) {
                changeCall("color");
            }
            if (source == "delete") {
                size = data.size();
                Figure fig;
                for (int i = size - 1; i >= 0; i--) {
                    fig = (Figure) data.get(i);
                    if (fig.isInside(x, y)) {
                        data.remove(i);
                        if (data.size() == 0) panelDBstate.setText("Active DB empty");
                        repaint();
                        break;
                    }
                }
            }
            if (source == about) {
                String text = "After choosing particular figure by Figure menu\n" +
                            "left click at the figure - drawing the figure\n" +
                            "right click at the figure - popum menu ";
                JOptionPane.showMessageDialog(null, text);
            }
        }

        public void changeCall(String info) {
            size = data.size();
            for (int i = size - 1; i >= 0; i--) {
                figure = (Figure) data.get(i);
                if (figure.isInside(x, y)) {
                    if (info.equals("point")) figure.change("point");
                    else if (info.equals("dim")) figure.change("dim");
                    else if (info.equals("color")) figure.changeColor();
                    else return;
                    repaint();
                    break;
                }
            }
        }
    }

    class MouseHandler implements MouseListener {

        Figure fig;
        boolean found;

        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();

            if (!e.isMetaDown()) {

                if (source == rectangle && !dragged.isSelected()) {
                    drawCall("rectangle");
                }
                if (source == circle && !dragged.isSelected()) {
                    drawCall("circle");
                }
                if (source == triangle && !dragged.isSelected()) {
                    drawCall("triangle");
                }
                if (dragged.isSelected()) {
                    size = data.size();
                    found = false;
                    for (int i = size - 1; i >= 0; i--) {
                        fig = (Figure) data.get(i);
                        if (fig.isInside(x, y)) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        dx = x - fig.point.x;
                        dy = y - fig.point.y;
                    }
                }
            }
        }

        public void mouseReleased(MouseEvent e) {
            found = false;
            if (e.isPopupTrigger()) {
                x = e.getX();
                y = e.getY();
                change.show(e.getComponent(), x, y);
            }
        }

        public void mouseDragged(MouseEvent e) {
            if (dragged.isSelected() && found) {
                fig.point = new Point(e.getX() - dx, e.getY() - dy);
                fig.initShape();
                repaint();
            }
        }

        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}

        public void drawCall(String info) {
            point = new Point(x, y);
            color = new Color(rand.nextInt());
            if (info.equals("rectangle"))
                figure = new Rect(point, new Dimension(40, 40), color);
            else if (info.equals("circle"))
                figure = new Circle(point, new Dimension(40, 40), color);
            else if (info.equals("triangle"))
                figure = new Triangle(point, new Dimension(40, 40), color);
            else return;
            figure.draw(g);
            database.add(figure);
            databaseAll.add(figure);
            panelDBstate.setText("Active DB filled");
        }
    }

    abstract class Figure {
        protected Color color;
        protected Point point;
        protected Dimension dim;
        Shape shape;

        public Figure(Point point, Dimension dim, Color color) {
            this.point = point;
            this.dim = dim;
            this.color = color;
        }

        abstract void initShape();

        public void draw(Graphics2D g2D) {
            g2D.setColor(color);
            g2D.fill(shape);
        }

        public boolean isInside(int x, int y) {
            return shape.contains(x, y);
        }

        public void change(String info) {
            StringTokenizer st;
            String input, message = "";
            int ct, a, b;

            if (info.equals("point"))message = "Enter new position(x, y)";
            else if (info.equals("dim"))
                message = "Enter new dimension (dim x, dim y)";
            else return;

            lab: while(true) {
                do {
                    input = JOptionPane.showInputDialog(null, message);
                    if (input == null) return;
                    st = new StringTokenizer(input);
                    ct = st.countTokens();
                } while (ct != 2);
                try {
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    break lab;
                } catch (NumberFormatException e) {
                    continue lab;
                }
            }

            if (info.equals("point")) {
                this.point.x = a;
                this.point.y = b;
            } else {
                this.dim.width = a;
                this.dim.height = b;
            }
        }
    
        public void changeColor() {
            this.color =
                JColorChooser.showDialog(null, "choose some color", Color.blue);
        }
    }

    public class Rect extends Figure {
        public Rect(Point point, Dimension dim, Color color) {
            super(point, dim, color);
            initShape();
        }

        public void initShape() {
            shape = new Rectangle(point, dim);
        }

        public void change(String str) {
            super.change(str);
            initShape();
        }
    }

    public class Circle extends Figure {
        public Circle(Point point, Dimension dim, Color color) {
            super(point, dim, color);
            initShape();
        }

        public void initShape() {
            shape = 
                new Arc2D.Float(point.x, point.y, dim.width, dim.height, 0, 360, 2);
        }

        public void change(String str) {
            super.change(str);
            initShape();
        }
    }

    public class Triangle extends Figure {
        public Triangle(Point point, Dimension dim, Color color) {
            super(point, dim, color);
            initShape();
        }

        public void initShape() {
            Polygon poly = new Polygon();
            poly.addPoint(point.x + dim.width / 2, point.y);
            poly.addPoint(point.x, point.y + dim.height);
            poly.addPoint(point.x + dim.width, point.y + dim.height);
            shape = poly;
        }

        public void change(String str) {
            super.change(str);
            initShape();
        }
    }
}

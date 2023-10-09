import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Kulki extends JPanel implements ActionListener{
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Kulki");
        Container cp = frame.getContentPane();
        Kulki kulki = new Kulki();
        cp.add(kulki);
        frame.addKeyListener(kulki.bar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(300, 300);
        frame.pack();
        frame.show();kulki.startGame();
    }

    public static final Dimension SIZE = new Dimension(300, 300);
    public static final int REFRESH = 40;

    private Bar bar = new Bar();
    private Ball[] balls = {
        new Ball(Color.magenta, bar),
        new Ball(Color.green, bar),
        new Ball(Color.blue, bar),
        new Ball(Color.red, bar)
    };
    private Image image;
    private Graphics graph;

    public Kulki() {
        setPreferredSize(Kulki.SIZE);
    }

    public void startGame() {
        image = createImage(SIZE.width, SIZE.height);
        graph = image.getGraphics();
        for (int i = 0; i < balls.length; i++) {
            balls[i].start();
        }
        Timer timer = new Timer(REFRESH, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null)
            g.drawImage(image, 0, 0, this);
    }

    public void actionPerformed(ActionEvent e) {
        graph.setColor(Color.cyan);
        graph.fillRect(0, 0, SIZE.width, SIZE.height);
        bar.draw(graph);
        for (int i = 0; i < balls.length; i++) {
            balls[i].draw(graph);
        }
        repaint();
    }
}

class Ball implements Runnable {

    public static final int SPEED = Kulki.REFRESH;
    public static final int SIZE = 25;
    public static final int MAX_VEL = 4;
    public static final int MIN_VEL = 2;

    private int dx, dy;
    private Point pos;
    private Color color;
    private boolean running = true;
    private Bar bar;

    public Ball(Color c, Bar b) {
        color = c;
        bar = b;
        double x = (Kulki.SIZE.width - SIZE) * Math.random();
        double y = (Kulki.SIZE.height - Bar.SIZE.height - SIZE) * Math.random();
        pos = new Point((int) x, (int) y);
        int d = MAX_VEL - MIN_VEL;
        dy = (-1) * (int) (d * Math.random() + MIN_VEL);
        dx = (int) (2 * MAX_VEL * Math.random() - MAX_VEL);
    }

    public void start() {
        new Thread(this).start();
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(SPEED);
            } catch (InterruptedException e) {
            }
            synchronized(pos) {
                pos.translate(dx, dy);
            }
            if (pos.x < 0 || pos.x > Kulki.SIZE.width - SIZE) 
                dx = -dx;
            if (dy > 0 && bar.bump(pos))
                dy = -dy;
            if (pos.y < 0)
                dy = -dy;
            if (pos.y > Kulki.SIZE.height - SIZE) {
                running = false;
            }
        }
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(color);
            synchronized(pos) {
                g.fillOval(pos.x, pos.y, SIZE, SIZE);
            }
        }
    }
}

class Bar extends KeyAdapter {

    public static final int BAR_VEL = 20;
    public static final Dimension SIZE = new Dimension(80, 10);
    private int pos;

    public Bar() {
        pos = (Kulki.SIZE.width - SIZE.width) / 2;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                synchronized(this) {
                    if (pos > 0)
                        pos -= BAR_VEL;
                }
                break;
            case KeyEvent.VK_RIGHT:
                synchronized(this) {
                    if (pos + SIZE.width < Kulki.SIZE.width)
                        pos += BAR_VEL;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
        }
    }

    public synchronized boolean bump(Point p) {
        return  p.y + Ball.SIZE > Kulki.SIZE.height - SIZE.height &&
                p.x + Ball.SIZE / 2 < pos + SIZE.width &&
                p.x + Ball.SIZE / 2 > pos;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fill3DRect(pos, Kulki.SIZE.height - SIZE.height - 1,
                     SIZE.width, SIZE.height, true);
    }
}

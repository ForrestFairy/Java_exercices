import java.awt.*;
import javax.swing.*;

public class WinterApp extends JApplet implements Runnable {
    private SnowMan snowy;
    private SnowFlake[] flakes = new SnowFlake[100];
    private Image img;
    private Graphics buf;
    private Thread animator;

    public void init() {
        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < flakes.length; i++) {
            flakes[i] = new SnowFlake(height, width);
        }
        snowy = new SnowMan(width, height);
        img = createImage(width, height);
        buf = img.getGraphics();
    }

    public void start() {
        animator = new Thread(this);
        animator.start();
    }

    public void stop() {
        animator = null;
    }

    public void run() {
        Thread me = Thread.currentThread();

        while (animator == me) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                repaint();
            }
        }
    }

    public void paint(Graphics g) {
        buf.setColor(new Color(0, 150, 0));
        buf.fillRect(0, 0, getWidth(), getHeight());

        snowy.paint(buf);

        for (int i = 0; i < flakes.length; i++) {
            flakes[i].paint(buf);
        }
        for (int i = 0; i < flakes.length; i++) {
            flakes[i].fall();
        }
        g.drawImage(img, 0, 0, null);
    }
}

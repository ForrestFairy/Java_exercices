import java.awt.*;
import javax.swing.*;

public class DrawPanel extends JPanel {
    
    int w, h;
    // poprzedni panel
    int wp, hp;
    // rozmiar i przemieszczenie kwadratów
    private int d = 10, dx = 20, dy = 20;
    private int gx, gy;

    int delay1, delay2;

    boolean upperReady = false;
    boolean lowerReady = false;

    boolean upperClear = false;
    boolean lowerClear = false;

    Image buffer;
    Graphics gb;

    public final Object lock = new Object();

    private Thread thread1, thread2, thread3;

    public DrawPanel(int delay1, int delay2) {
        this.delay1 = delay1;
        this.delay2 = delay2;
    }

    public void initGraphics() {
        w = getWidth();
        h = getHeight();
        buffer = createImage(w, h);
        gb = buffer.getGraphics();
        wp = w;
        hp = h;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (buffer != null)
            g.drawImage(buffer, 0, 0, null);
        w = getWidth();
        h = getHeight();
        if (w != wp || h != hp) {
            interruptThreads();
            buffer = createImage(w, h);
            gb = buffer.getGraphics();
            wp = w; hp = h;

            if (buffer != null) {
                g.drawImage(buffer, 0, 0, null);
            }

            if (notNullThreads()) {
                if (notAliveThreads()) {
                    createThreads();
                    startThreads();
                } else {
                    wp = -1;
                    repaint();
                }
            }
        }
    }

    public void createThreads() {
        thread1 = new Thread1();
        thread2 = new Thread2();
        thread3 = new Thread3();
    }

    public void startThreads() {
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public void interruptThreads() {
        if(thread1 != null) thread1.interrupt();
        if(thread2 != null) thread2.interrupt();
        if(thread3 != null) thread3.interrupt();
    }

    public boolean notAliveThreads() {
        return !thread1.isAlive() && !thread2.isAlive() && !thread3.isAlive();
    }

    public boolean notNullThreads() {
        return thread1 != null && thread2 != null && thread3 != null;
    }

    private class Thread1 extends Thread {

        public void run() {
            upperReady = false;
            int x1 = -15, y1 = 5;

            while (!isInterrupted()) {
                synchronized(lock) {
                    while (upperReady || lowerReady) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    x1 += dx;
                    if (x1 + d >= w) { x1 = 5; y1 += dy; }
                    if (y1 + d >= h / 2) { upperClear = true; x1 = 5; y1 = 5; }
                    gx = x1; gy = y1;
                    upperReady = true;
                    lock.notifyAll();
                }

                try {
                    Thread.sleep(delay1);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    private class Thread2 extends Thread {

        public void run() {
            lowerReady = false;
            int x2 = -15, y2 = h / 2 + 5;

            while (!isInterrupted()) {
                synchronized(lock) {
                    while (upperReady || lowerReady) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    x2 += dx;
                    if (x2 + d >= w) { x2 = 5; y2 += dy; }
                    if (y2 + d >= h) { lowerClear = true; x2 = 5; y2 = h / 2 + 5; }
                    gx = x2; gy = y2;
                    lowerReady = true;
                    lock.notifyAll();
                }

                try {
                    Thread.sleep(delay2);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    private class Thread3 extends Thread {

        public void run() {
            while (!isInterrupted()) {
                synchronized(lock) {
                    while (!upperReady && !lowerReady) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    if (upperClear) {
                        gb.clearRect(0, 0, w, h / 2);
                        upperClear = false;
                    }
                    if (lowerClear) {
                        gb.clearRect(0, h / 2, w, h / 2);
                        lowerClear = false;
                    }

                    if (gy < h / 2) gb.setColor(Color.blue);
                    if (gy > h / 2) gb.setColor(Color.green);

                    gb.drawRect(gx, gy, d, d);
                    gb.setColor(Color.red);
                    gb.drawLine(0, h / 2, w, h / 2);
                    repaint();

                    if (upperReady) upperReady = false;
                    if (lowerReady) lowerReady = false;

                    lock.notifyAll();
                }
            }
        }
    }
}

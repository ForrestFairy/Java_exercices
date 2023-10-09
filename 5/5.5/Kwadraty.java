import java.awt.*;
import javax.swing.*;
import java.lang.reflect.*;

public class Kwadraty extends JFrame {
    
    Container cp;
    DrawPanel dp;

    public static void main(String[] args) {
        new Kwadraty(args);
    }

    public Kwadraty(String[] args) {
        super("Wykreslanie kwadratow przez 3 watki");
        setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int delay1, delay2;
        delay1 = Integer.parseInt(args[0]);
        delay2 = Integer.parseInt(args[1]);

        dp = new DrawPanel(delay1, delay2);
        dp.setBackground(Color.white);
        dp.setPreferredSize(new Dimension(500, 400));
        cp = getContentPane();
        cp.add(dp, "Center");

        pack();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                show();
                dp.initGraphics();
                dp.createThreads();
                dp.startThreads();
            }
        });

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                dp.interruptThreads();
            }
        });
    }
}

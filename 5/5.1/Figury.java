import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

public class Figury extends JFrame {
    
    Container cp;
    DrawPanel dp;
    int a, b;

    public static void main(String[] args) {
        new Figury();
    }

    public Figury() {
        super("FIGURY: wykreślanie figur,zmiana pozycji,rozmiaru,koloru");
        setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cp = getContentPane();

        JPanel hp = new JPanel();
        hp.setPreferredSize(new Dimension(80, 80));
        cp.add(hp, "North");

        inputSize();
        dp = new DrawPanel(a, b);
        cp.add(dp, "Center");

        dp.initMenu();

        pack();
        show();
    }

    public void inputSize() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        String message = "Podaj preferowane rozmiary panelu";
        String title =
            "Rozmiary ekranu: " + "w=" + dim.width + " h=" + dim.height;
        String input = "";
        StringTokenizer st = null;
        int ct = 0;

        lab: while(true) {
            do {
                input = JOptionPane.showInputDialog(null, message, title, 3);
                if (input == null) continue;
                st = new StringTokenizer(input);
                ct = st.countTokens();
            } while(ct != 2);
            try {
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                break lab;
            } catch (NumberFormatException e) {
                continue lab;
            }
        }
    }
}

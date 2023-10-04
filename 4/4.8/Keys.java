import java.util.TreeMap;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;

public class Keys extends JFrame {
    
    Container cp = getContentPane();

    String[] keys = {"AltW", "AltK", "AltP"};
    String[] txt = {"Warszawa", "Krakow", "Poznan"};
    KbShort ks = new KbShort(keys, txt);

    public Keys() {
        cp.setLayout(new FlowLayout());
        addComponent(new FocusLabel("Miasto1").getLabel());
        addComponent(new FocusLabel("Miasto2").getLabel());
        addComponent(new JTextField(10));
        addComponent(new JButton("Przycisk"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        show();
    }

    void addComponent(JComponent c) {
        c.addKeyListener(ks);
        cp.add(c);
    }

    public static void main(String[] args) {
        new Keys();
    }
}

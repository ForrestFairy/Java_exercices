import java.util.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.event.*;

public class KbShort extends KeyAdapter {

    TreeMap hm = new TreeMap();

    KbShort(String[] keys, String[] txt) {
        for (int i = 0; i < keys.length; i++) hm.put(keys[i], txt[i]);
    }

    public void keyReleased(KeyEvent key) {
        int k = key.getKeyCode();
        int m = key.getModifiers();
        String t =
            (String) hm.get(KeyEvent.getKeyModifiersText(m) + KeyEvent.getKeyText(k));

        if (t == null) return;
        Object o = key.getSource();
        if (o instanceof JLabel) ((JLabel) o).setText(t);
        else if (o instanceof AbstractButton) ((AbstractButton) o).setText(t);
        else if (o instanceof JTextComponent) ((JTextComponent) o).setText(t);
    }
}
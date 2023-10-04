import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FocusLabel extends MouseAdapter implements FocusListener {
    
    private JLabel lab;

    public FocusLabel(String txt) {
        lab = new JLabel(txt);
        lab.addMouseListener(this);
        lab.addFocusListener(this);
    }

    public JLabel getLabel() {
        return lab;
    }

    public void mousePressed(MouseEvent e) {
        lab.requestFocus();
    }

    public void focusGained(FocusEvent e) {
        lab.setBorder(BorderFactory.createLineBorder(Color.red));
    }

    public void focusLost(FocusEvent e) {
        lab.setBorder(null);
    }
}

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

public class ModifyPanel extends JPanel {
    
    private JTextField[] tf;
    private JLabel[] lab;
    private JButton ok;
    private String[] data;
    private DefaultTableModel dtm;

    public ModifyPanel(DefaultTableModel mod, ActionListener al) {
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridLayout(0, 2, 10, 10));
        dtm = mod;
        
        int n = dtm.getColumnCount() - 1;
        data = new String[n + 1];
        tf = new JTextField[n];
        lab = new JLabel[n];

        for (int i = 0; i < n; i++) {
            lab[i] = new JLabel(dtm.getColumnName(i + 1));
            lab[i].setHorizontalAlignment(SwingConstants.RIGHT);
            add(lab[i]);
            tf[i] = new JTextField();
            add(tf[i]);
        }

        ok = new JButton("AKCEPTUJ DANE");
        ok.addActionListener(al);
        add(new JPanel());
        add(ok);
    }

    public void setCommandAndFocus(String cmd) {
        ok.setActionCommand(cmd);
        tf[0].requestFocus();
    }

    public String[] getModifData() {
        for (int i = 0; i < tf.length; i++) {
            data[i + 1] = tf[i].getText();
            tf[i].setText("");
        }
        return data;
    }
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Recorder extends JFrame {
    
    JButton bnum[] = new JButton[10];
    JToggleButton record = new JToggleButton("Recording", new ImageIcon("green.gif"));
    JButton play = new JButton("Play");

    java.util.List playList = new ArrayList();

    Recorder() {
        record.setSelectedIcon(new ImageIcon("red.gif"));

        ActionListener buttAct = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
            }
        };

        JPanel p = new JPanel(new GridLayout(3, 0));

        for (int i = 0; i < bnum.length; i++) {
            bnum[i] = new JButton("" + i);
            bnum[i].addActionListener(buttAct);
            p.add(bnum[i]);
        }

        getContentPane().add(p, "Center");

        final ActionListener recordAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playList.add(e.getSource());
            }
        };

        record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (((JToggleButton) e.getSource()).isSelected()) {
                    play.setEnabled(false);
                    playList.clear();
                    for (int i = 0; i < bnum.length; i++) {
                        bnum[i].addActionListener(recordAction);
                    }
                } else {
                    for (int i = 0; i < bnum.length; i++) {
                        bnum[i].removeActionListener(recordAction);
                    }
                    if (playList.size() > 0) play.setEnabled(true);
                }
            }
        });

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println('\n');
                Iterator it = playList.iterator();
                while (it.hasNext()) ((JButton) it.next()).doClick();
            }
        });

        JPanel pcon = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pcon.setBorder(BorderFactory.createLineBorder(Color.blue));
        pcon.add(record);
        pcon.add(play);
        getContentPane().add(pcon, "South");

        pack();
        show();
    }

    public static void main(String[] args) {
        new Recorder();
    }
}

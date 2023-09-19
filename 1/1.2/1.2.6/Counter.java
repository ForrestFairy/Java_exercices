import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Counter extends JFrame implements ActionListener {
    
    public static void main(String[] args) {
        new Counter();
    }

    int counter = 0;
    final int N = 5;
    int[] savedNumbers = new int[N];
    int numOfSaved = 0;

    JButton incButton = new JButton("+");
    JButton decButton = new JButton("-");
    JButton saveButton = new JButton("Save");

    JLabel cLab = new JLabel("  0  ");

    Counter() {
        incButton.addActionListener(this);
        decButton.addActionListener(this);
        saveButton.addActionListener(this);
        cLab.setPreferredSize(new Dimension(60, 40));
        cLab.setHorizontalAlignment(JLabel.CENTER);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(incButton);
        cp.add(cLab);
        cp.add(decButton);
        cp.add(saveButton);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (numOfSaved > 0) reportSumAndMean();
                dispose();
                System.exit(0);
            }
        });

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == incButton) counter++;
            else if (o == decButton) counter--;
                else if (o == saveButton) {
                    try {
                        savedNumbers[numOfSaved] = counter;
                        numOfSaved++;
                    } catch (ArrayIndexOutOfBoundsException exc) {
                        Toolkit.getDefaultToolkit().beep();
                        saveButton.setEnabled(false);
                    }
                }
                cLab.setText("  " + counter + "  ");
    }

    void reportSumAndMean() {
        float sum = 0;
        float mean;
        for (int i = 0; i < numOfSaved; i++) {
            sum += savedNumbers[i];
        }
        mean = sum / numOfSaved;
        System.out.println("Suma wynosi " + sum);
        System.out.println("Srednia wynosi " + mean);
    }
}

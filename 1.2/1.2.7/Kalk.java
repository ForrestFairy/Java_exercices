import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Kalk extends JFrame implements ActionListener{
    
    public static void main(String[] args) {
        try {
            double kurs = Double.parseDouble(args[0]);
            new Kalk(kurs);
        } catch (Exception e) {
            System.out.println("Brak albo wadliwy kurs podany");
            System.exit(1);
        }
    }

    double kurs;
    JButton fromEur = new JButton(">>");
    JButton fromPln = new JButton("<<");
    JTextField eur = new JTextField(10);
    JTextField pln = new JTextField(10);

    Kalk(double k) {
        kurs = k;
        
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.setFont(new Font("Dialog", Font.BOLD, 18));

        pln.setBorder(BorderFactory.createTitledBorder("PLN"));
        eur.setBorder(BorderFactory.createTitledBorder("EUR"));

        fromEur.addActionListener(this);
        fromPln.addActionListener(this);
        pln.addActionListener(this);
        eur.addActionListener(this);

        cp.add(eur);
        cp.add(fromEur);
        cp.add(fromPln);
        cp.add(pln);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        try {
            if (o == eur || o == fromEur) {
                double wynik = Double.parseDouble(eur.getText()) * kurs;
                pln.setText("" + wynik);
            } else if (o == pln || o == fromPln) {
                double wynik = Double.parseDouble(pln.getText()) / kurs;
                eur.setText("" + wynik);
            }
        } catch (NumberFormatException exc) {
            return;
        }
    }
}

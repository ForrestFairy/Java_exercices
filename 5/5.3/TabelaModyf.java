import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class TabelaModyf extends JFrame implements ActionListener {
    
    private Container cp = getContentPane();
    private ModifyPanel mp;
    private JTable table;

    public TabelaModyf() {
        super("Tabela-dodawanie,wstawianie,usuwanie");

        String[] kolumny = {"Nr porzadkowy", " Nazwa towaru", "Producent", "Cena"};

        String[][] dane = { {"", "Farba-Ocynk", "Hammerite", "122"},
                            {"", "Farba-Kaloryfer", "Hammerite", "109"},
                            {"", "Super Wood Stein", "Dyrup", "34"},
                            {"", "Dyroton-6", "Dyrup", "54"}                  
                        };
        MyTableModel dtm = new MyTableModel(dane, kolumny);
        table = new JTable(dtm);

        table.setRowHeight(20);
        table.setBorder(BorderFactory.createLineBorder(Color.blue));
        table.setGridColor(Color.blue);

        TableColumn cena = table.getColumn("Cena");
        DefaultTableCellRenderer cenaCr = new DefaultTableCellRenderer();
        cenaCr.setBackground(Color.yellow);
        cenaCr.setForeground(Color.red);
        cena.setCellRenderer(cenaCr);

        JPanel ster = createControlPanel();

        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

        cp.add(new JScrollPane(table));
        cp.add(ster);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        show();
    }

    JPanel createControlPanel() {
        String[] lab = { "Dodaj", "Wstaw", "Usun" };
        String[] akcje = { "modifAdd", "modifInsert", "Remove" };
        JPanel p = new JPanel();

        for (int i = 0; i < akcje.length; i++) {
            JButton b = new JButton(lab[i]);
            b.setActionCommand(akcje[i]);
            b.addActionListener(this);
            p.add(b);
        }

        return p;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();

        if (cmd.equals("Remove")) {
            int[] index = table.getSelectedRows();
            if (index != null)
                for (int i = index.length - 1; i >= 0; i--)
                    dtm.removeRow(index[i]);
        } else if (cmd.startsWith("modif")) {
            if (mp == null) mp = new ModifyPanel(dtm, this);
            cp.add(mp);
            cp.validate();
            mp.setCommandAndFocus(cmd.substring(5));
        } else {
            String[] data = mp.getModifData();
            if (cmd.equals("Add")) dtm.addRow(data);
            else dtm.insertRow(table.getSelectedRow() + 1, data);
            cp.remove(mp);
            cp.validate();
        }
    }

    public static void main(String[] args) {
        new TabelaModyf();
    }
}

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Editor {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                "Motif");

        // UIManager.getSystemLookAndFeelClassName() -> to wywołuje błąd:
        // (java:14071): Gtk-WARNING **: 12:25:55.987: Unable to locate theme engine in module_path: "pixmap"
        // dlatego użyty Motif
        } catch (Exception e) {
        }
        new Editor();
    }

    private JFrame frame = new JFrame("Edytor");
    private JTextArea textArea = new JTextArea(10, 20);
    private JFileChooser fileChooser = new JFileChooser(".");
    private JTextField textField = new JTextField(10);

    private Action open = new AbstractAction("Open", new ImageIcon("open.gif")) {
        public void actionPerformed(ActionEvent ae) {
            if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                readFile(fileChooser.getSelectedFile());
            }
        }
    };

    private Action close = new AbstractAction("Close", new ImageIcon("close.gif")) {
        public void actionPerformed(ActionEvent ae) {
            closeFile();
        }
    };

    private Action save = new AbstractAction("SaveAs", new ImageIcon("save.gif")) {
        public void actionPerformed(ActionEvent ae) {
            if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                saveFile(fileChooser.getSelectedFile());
            }
        }
    };

    public Editor() {
        Container content = frame.getContentPane();
        JMenuBar menuBar = makeMenu();
        JToolBar toolBar = makeToolBar();
        makePopup();
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);

        scrollPane.setPreferredSize(new Dimension(400, 300));
        content.setLayout(new BorderLayout());
        content.add(toolBar, BorderLayout.NORTH);
        content.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    private JToolBar makeToolBar() {
        JToolBar tb = new JToolBar();
        JLabel file = new JLabel("File name: ");
        textField.setMaximumSize(new Dimension(100, 21));

        tb.addSeparator();
        tb.add(open);
        tb.add(close);
        tb.add(save);
        tb.addSeparator();
        tb.add(file);
        tb.add(textField);

        return tb;
    }

    private JMenuBar makeMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenu = new JMenuItem("Exit");

        fileMenu.add(open);
        fileMenu.add(close);
        fileMenu.add(save);
        fileMenu.addSeparator();
        fileMenu.add(exitMenu);
        mb.add(fileMenu);

        exitMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        return mb;
    }

    private JPopupMenu makePopup() {
        final JPopupMenu pop = new JPopupMenu();
        pop.add(open);
        pop.add(close);
        pop.add(save);

        textArea.addMouseListener(new MouseAdapter() {
            public void MouseReleased(MouseEvent me) {
                if (me.isPopupTrigger())
                    pop.show(textArea, me.getX(), me.getY());
            }
        });
        return pop;
    }

    private void readFile(File file) {
        textField.setText(file.getName());

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            textArea.setText("");

            String line;
            while ( (line = br.readLine()) != null) {
                textArea.append(line + "\n");
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveFile(File file) {
        textField.setText(file.getName());

        try {
            FileWriter fw = new FileWriter(file);
            String text = textArea.getText();

            fw.write(text);
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeFile() {
        File file;
        String fileName = textField.getText();

        if (fileName.length() > 0) {
            file = new File(fileName);
        } else {
            textField.setText("brak nazwy pliku");
            return;
        }

        saveFile(file);
        textField.setText("");
        textArea.setText("");
    }
}

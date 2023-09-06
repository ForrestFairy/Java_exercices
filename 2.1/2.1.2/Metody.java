import javax.swing.*;

public class Metody {
    
    public static void main(String[] args) {
        String s = getData();
        String t = s.toUpperCase();
        showData(t);
        System.exit(0);
    }

    public static String getData() {
        String retval = JOptionPane.showInputDialog("Podaj dane");

        while (retval == null || retval.equals("")) {
            retval = JOptionPane.showInputDialog("Brak danych, sproboj jeszcze raz");
        }
        return retval;
    }

    public static void showData(String data) {
        JOptionPane.showMessageDialog(null, data);
    }
}

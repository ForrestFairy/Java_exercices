import java.util.*;
import javax.swing.*;

public class Parser {

    private int result;

    public Parser(String exprStr) {
        ArrayList plus = parsePlus(exprStr);
        result = countAll(plus);
    }

    private ArrayList parsePlus(String expr) {
        ArrayList exprList = new ArrayList();
        StringTokenizer plus = new StringTokenizer(expr, "+");
        while (plus.hasMoreTokens()) {
            exprList.add(plus.nextToken());
        }
        return exprList;
    }

    private int parseMult(String expr) {
        int res = 1;
        StringTokenizer mult = new StringTokenizer(expr, "*");
        while (mult.hasMoreTokens()) {
            String sVal = mult.nextToken();
            int iVal = Integer.parseInt(sVal);
            res *= iVal;
        }
        return res;
    }

    private int countAll(ArrayList exprList) {
        int val = 0;
        for (int i = 0; i < exprList.size(); i++) {
            String expr = (String) exprList.get(i);
            val += parseMult(expr);
        }

        return val;
    }

    public int getResult() {
        return result;
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

    public static void main(String[] args) {
        String data;

        while ( (data = getData()) != null ) {
            try {
                Parser parse = new Parser(data);
                showData("" + parse.getResult());
            } catch (Exception e) {
                showData("Blad w wyrazeniu: " + e.getMessage());
            }
        }
        System.exit(0);
    }
}

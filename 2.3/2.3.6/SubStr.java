public class SubStr {

    private String src;
    private String[][] subStr;

    public SubStr(String s) {
        if (s == null) {
            s = new String();
        }
        src = s.trim();
        subStr = new String[src.length()][];
    }

    // nie uwzględnia powtarzalności
    public SubStr fillTab() {
        for (int i = 1; i <= subStr.length; i++) {
            String[] subTab = new String[src.length() - i + 1];
            for (int j = 0; j < subTab.length; j++) {
                subTab[j] = src.substring(j, j + i);
            }
            subStr[i - 1] = subTab;
        }
        return this;
    }

    public SubStr fillTabDiff() {
        for (int i = 1; i <= subStr.length; i++) {
            String[] tmpTab = new String[src.length() - i + 1];
            int ind = 0;
            for (int j = 0; j < src.length() - i + 1; j++) {
                String sub = src.substring(j, j + i);
                if (!exists(tmpTab, sub))
                    tmpTab[ind++] = sub;
            }
            subStr[i - 1] = new String[ind];

            System.arraycopy(tmpTab, 0, subStr[i - 1], 0, ind);
        }
        return this;
    }

    // Metoda pomocnicza do sprawdzenia czy string występuje juz w tablicy
    private boolean exists(String[] t, String s) {
        if (t == null || s == null) {
            return false;
        }
        for (int i = 0; i < t.length; i++) {
            if (s.equals(t[i]))
                return true;
        }
        return false;
    }

    public void show() {
        for (int i = 0; i < subStr.length; i++) {
            if (subStr[i] == null) {
                continue;
            }
            System.out.print(
                "Dlugosc = " + (i + 1) + " liczba = " + subStr[i].length + " : "
            );
            for (int j = 0; j < subStr[i].length; j++) {
                System.out.print("'" + subStr[i][j] + "' ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        String data;
        if (args.length > 0) {
            data = args[0];
        } else {
            data = " kukuryku ";
        }

        System.out.println("Podlancuchy lancucha '" + data + "' : ");
        new SubStr(data).fillTab().show();

        System.out.println("Rozne podlancuchy lancucha '" + data + "' : ");
        new SubStr(data).fillTabDiff().show();
    }
}

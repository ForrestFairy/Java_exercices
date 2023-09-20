import javax.swing.JOptionPane;

public class Wektor {
    
    private float tab[];
    private int size;

    private Wektor() {
        tab = null;
        size = 0;
    }

    public Wektor(int initSize) {
        if (initSize < 1) {
            System.out.println("Tablica musi miec przynajmniej jeden element");
            System.exit(0);
        }

        int index = 0;
        String str = "";
        float f;
        tab = new float[initSize];

        while (str != null || size == 0) {
            str = JOptionPane.showInputDialog("Podaj element-" + index + " wektora");

            try {
                f = Float.parseFloat(str);

                if (tab.length < index + 1) {
                    initSize = 2 * initSize;
                    float[] tab2 = new float[initSize];
                    System.arraycopy(tab, 0, tab2, 0, index);
                    tab = tab2;
                    tab2 = null;
                }

                tab[index] = f;
                size = index + 1;
                index++;
            } catch (NullPointerException e) {}
        }
    }

    public void show(String kom) {
        System.out.print(kom + " =[");
        for (int i = 0; i < size - 1; i++) {
            System.out.print("" + tab[i] + ",");
        }
        System.out.println(tab[size - 1] + "]");
    }

    public Wektor add(Wektor w) {
        if (size != w.size) {
            System.out.println("wektory musza miec ta sama ilosc elementow !");
            System.exit(0);
        }
        Wektor wynik = new Wektor();
        wynik.tab = new float[w.size];
        wynik.size = size;

        for (int i = 0; i < size; i++) {
            wynik.tab[i] = tab[i] + w.tab[i];
        }
        return wynik;
    }

    public Wektor sub(Wektor w) {
        if (size != w.size) {
            System.out.println("wektory musza miec ta sama ilosc elementow !");
            System.exit(0);
        }
        Wektor wynik = new Wektor();
        wynik.tab = new float[w.size];
        wynik.size = size;

        for (int i = 0; i < size; i++) {
            wynik.tab[i] = tab[i] - w.tab[i];
        }
        return wynik;
    }

    public Wektor mult(float f) {
        Wektor wynik = new Wektor();
        wynik.tab = new float[size];
        wynik.size = size;

        for (int i = 0; i < size; i++) {
            wynik.tab[i] = tab[i] * f;
        }
        return wynik;
    }

    public static void main(String[] args) {
        Wektor wynik;

        Wektor w1 = new Wektor(1);
        w1.show("w1");
        Wektor w2 = new Wektor(1);
        w2.show("w2");

        wynik = w1.add(w2);
        wynik.show("w1 + w2");
        wynik = w1.sub(w2);
        wynik.show("w1 - w2");
        wynik = w1.mult(2);
        wynik.show("2 * w1");
        wynik = w2.mult(3);
        wynik.show("3 * w2");

        System.exit(0);
    }
}

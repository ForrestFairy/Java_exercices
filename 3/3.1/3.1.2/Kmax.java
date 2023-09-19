public class Kmax {

    public int[] swap(int[] tab, int i, int j) {
        int temp = tab[i];
        tab[i] = tab[j];
        tab[j] = temp;

        return tab;
    }

    public int maxk(int[] tab, int k) {
        int max = 0; 
        int indeksMax = 0;

        int[] tabLok = new int[tab.length];

        for (int i = 0; i < tab.length; i++) 
            tabLok[i] = tab[i];

        int maxPoprz = tabLok[0];

        for (int j = 0; j < k; j++) {
            max = tabLok[0];
            indeksMax = 0;
            for (int i = 0; i < tabLok.length - j; i++) {
                if (max < tabLok[i]) {
                    max = tabLok[i];
                    indeksMax = i;
                }
            }

            tabLok = swap(tabLok, indeksMax, tabLok.length-1-j);

            System.out.println("\nZamiana w tablicy lokalnej\n tab = ");
            for (int i = 0; i < tabLok.length; i++) {
                if (i < tabLok.length-1) System.out.print(tabLok[i] + ",");
                if (i == tabLok.length-1) System.out.print(tabLok[i]);
            }
            if (j > 0 && max == maxPoprz) k++;
            maxPoprz = max; 
        }
        return max;
    }

    public Kmax() {
        int [] tab = {1, 8, 3, -8, 5, 6, 7, 8, -9, 9, 9, 10, 10, 8, 0};
        int max = 0;
        int ktore = 3;
        
        max = maxk(tab, ktore);

        System.out.print("\nTablica pierwotna\n tab = ");

        for (int i = 0; i < tab.length; i++) {
            if (i < tab.length-1) System.out.print(tab[i] + ",");
            if (i == tab.length-1) System.out.print(tab[i]);
        }

        System.out.println("\nmax " + ktore + " = " + max);
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == max) System.out.println("indmax = " + i);
        }
    }

    public static void main(String[] args) {
        new Kmax();
    }
}

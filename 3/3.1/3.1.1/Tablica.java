import java.util.Random;

public class Tablica {
    
    private int[] tab;

    public Tablica(int size) {
        tab = new int[size];
        fillRandom(tab);
    }

    public Tablica(int[] t) {
        if (t == null)
            throw new IllegalArgumentException();
        this.tab = (int[])t.clone();
    }

    private void fillRandom(int[] tab) {
        Random rand = new Random();
        for (int i = 0; i < tab.length; i++) {
            tab[i] = rand.nextInt(tab.length);
        }
    }

    private static int[] join(int[] head, int[] tail) {
        int newTab[] = new int[head.length + tail.length];
        System.arraycopy(head, 0, newTab, 0, head.length);
        System.arraycopy(tail, 0, newTab, head.length, tail.length);

        return newTab;
    }

    public Tablica append(int[] tail) {
        if (tail == null)
            throw new IllegalArgumentException();
        this.tab = join(this.tab, tail);
        return this;
    }

    public Tablica append(Tablica tail) {
        if (tail == null)
            throw new IllegalArgumentException();
        this.tab = join(tab, tail.tab);
        return this;
    }

    public int get(int index) {
        return tab[index];
    }

    public void set(int index, int value) {
        tab[index] = value;
    }

    public int findMaxInd() {
        if (tab.length < 1) {
            return -1;
        }
        int max = tab[0];
        int maxInd = 0;
        for (int i = 1; i < tab.length; i++) {
            if (tab[i] > max) {
                max = tab[i];
                maxInd = i;
            }
        }
        return maxInd;
    }

    public String toString() {
        String s = "";
        s += "Tablica liczb: [";
        for (int i = 0; i < tab.length - 1; i++) {
            s += tab[i] + ", ";
        }
        s += tab[tab.length - 1] + "]";
        
        return s;
    }

    public static void main(String[] args) {
        Tablica t = new Tablica(5);
        System.out.println(t);
        int[] head = {3, 4, 5};
        int[] tail = new int[] {6, 7, 8, 9};
        Tablica table = new Tablica(new int[] {1, 2});
        System.out.println(table);
        System.out.println(table.append(head));
        System.out.println(table.append(new Tablica(tail)));

        int size = new java.util.Random().nextInt(100);
        table = new Tablica(size);

        System.out.println(table);
        int maxInd = table.findMaxInd();
        int maxVal = table.get(maxInd);
        System.out.println("Max = " + maxVal);
    }
}

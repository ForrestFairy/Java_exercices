public class NumTab {
    
    private int[][] tab;

    public NumTab(int[][] t) {
        if (t == null)
            throw new IllegalArgumentException();
        tab = t;
    }

    public NumTab(int h, int w) {
        if (h < 0)
            h = Math.abs(h);
        if (w < 0)
            w = Math.abs(w);
        tab = fillRandom(new int[h][w]);
    }

    public static int[][] fillRandom(int[][] t) {
        if (t == null)
            return t;
        for (int h = 0; h < t.length; h++) {
            if (t[h] != null) {
                for (int w = 0; w < t[h].length; w++) {
                    t[h][w] = (int) (10 * Math.random());
                }
            }
        }
        return t;
    }

    private int makeInt(int[] dimension) {
        if (dimension == null)
            throw new IllegalArgumentException();

        int ans = 0;
        int power = 1;
        for (int i = dimension.length - 1; i >= 0; i--) {
            if (dimension[i] < 0 || dimension[i] > 9) {
                System.err.println("Zle dane: " + dimension[i]);
                dimension[i] = Math.abs(dimension[i] % 10);
            }
            ans += dimension[i] * power;
            power *= 10;
        }
        return ans;
    }

    public int sum() {
        int ans = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null)
                ans += makeInt(tab[i]);
        }
        return ans;
    }

    public void show() {
        for (int h = 0; h < tab.length; h++) {
            if (tab[h] != null) 
                for (int w = tab[h].length - 1; w >= 0; w--) {
                    System.out.print(tab[h][w] + " ");
                }
            System.out.println("");
        }
    }

    public void print() {
        for (int h = 0; h < tab.length; h++) {
            if (tab[h] != null) {
                System.out.println(makeInt(tab[h]));
            }
        }
    }

    public static void main(String[] args) {
        int [][] intTab = new int[][] {
            {1, 2, 3, 4},
            {},
            {5, 6, 7},
            {8, 9},
            null,
            {0}
        };
        NumTab tab = new NumTab(intTab);
        tab.print();
        int val = tab.sum();
        System.out.println("suma = " + val);

        NumTab losTab = new NumTab(3, 4);
        losTab.show();
        val = losTab.sum();
        System.out.println("suma = " + val);
    }
}

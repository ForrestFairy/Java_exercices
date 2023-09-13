public class Test {
    
    public static void report(BottleContainer[] bc) {
        System.out.println("Stan wody:");
        int lsum = 0, msum = 0, ssum = 0;
        double volume = 0;

        for (int i = 0; i < bc.length; i++) {
            System.out.println(bc[i]);
            ssum += bc[i].getSmall();
            msum += bc[i].getMedium();
            lsum += bc[i].getLarge();
            volume += bc[i].getVolume();
        }

        System.out.println("W sumie jest");
        System.out.println("Malych butelek: " + ssum);
        System.out.println("Srednich butelek: " + msum);
        System.out.println("Duzych butelek: " + lsum);
        System.out.println("Calkowita pojemnosc wody " + volume);
    }

    public static void putBottles(BottleContainer bc, int l, int m, int s) {
        bc.addLarge(l);
        bc.addMedium(m);
        bc.addSmall(s);
    }

    public static void main(String[] args) {
        Frige fr1 = new Frige("nr 1"),
              fr2 = new Frige("nr 2");

        Shelf waterShelf = new Shelf("przy oknie");

        putBottles(fr1, 10, 20, 7);
        putBottles(fr2, 5, 10, 3);
        putBottles(waterShelf, 5, 5, 5);

        report(new BottleContainer[] {fr1, fr2, waterShelf});
    }
}

public class TestWater {

    public static void report(String name, MyWater water) {
        System.out.println(name + ":");
        System.out.println("Malych butelek jest  : " + water.getSmall());
        System.out.println("Srednich butelek jest: " + water.getMedium());
        System.out.println("Duzych butelek jest  : " + water.getLarge());
        System.out.println("Laczny zapas wody    : " + water.getVolume());
    }

    public static void main(String[] args) {
        MyWater frige = new MyWater();

        frige.addLarge(2);
        frige.addMedium(3);
        frige.addSmall(5);

        report("Lodowka", frige);

        MyWater shelf = new MyWater(3, 2, 1);

        shelf.addLarge(1);

        report("Polka", shelf);

        frige.addLarge(1);
        frige.addMedium(10);

        report("Lodowka", frige);
    }
}
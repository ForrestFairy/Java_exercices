public class Suma {

    public static void main(String[] args) {
        int liczba = 0;
        int suma = 0;

        try {
            if (args.length != 3)
                throw new IllegalArgumentException();

            int tryb = Integer.parseInt(args[2]);
            liczba = Integer.parseInt(args[0]);
            suma = Integer.parseInt(args[1]);

            if (liczba < 0) 
                throw new IllegalArgumentException();

            if (tryb == 0) {
                poLimicie(liczba, suma);
            } else {
                przedLimitem(liczba, suma);
            }
        } catch (IllegalArgumentException e) {
            say("Uzycie: java Suma liczba suma tryb (0 - moze przekroczyc suma, 1 - bez przekraczania sumau)");
        } catch (Exception e) {

        }
    }

    public static void say(String napis) {
        System.out.println(napis);
    }

    public static void poLimicie(int liczba, int suma) {
        int sum = 0;
        int curr;
        for (curr = 1; curr <= liczba; curr++) {
            sum += curr;
            if (sum > suma) {
                say("Suma to " + sum + " ostatnia dodana liczba to " + curr);
                return;
            }
        }
        say("Suma to " + sum + " ostatnia dodana liczba to " + (curr - 1));
    }

    public static void przedLimitem(int liczba, int suma) {
        int sum = 0;
        int curr;
        for (curr = 1; curr <= liczba; curr++) {
            if (sum + curr > suma) {
                say("Suma to " + sum + " ostatnia dodana liczba to " + (curr - 1));
                return;
            }
            sum += curr;
        }
        say("Suma to " + sum + " ostatnia dodana liczba to " + (curr - 1));
    }
}
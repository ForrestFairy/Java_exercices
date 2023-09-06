public class Zad1 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Brak argumentow");
            return;
        }

        int suma = 0;

        for (String liczba : args) {
            try {
                int temp = Integer.parseInt(liczba);
                suma += temp;
            } catch (Exception e) {
                System.exit(2);
            }
            
        }

        System.out.println(suma);
    }
}

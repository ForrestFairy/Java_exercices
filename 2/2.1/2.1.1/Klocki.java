public class Klocki {
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uzycie: java Klocki liczbaKlockow ileSieMiesci");
            System.exit(0);
        }
        try {
            int liczbaKlockow = Integer.parseInt(args[0]);
            int ileSieMiesci = Integer.parseInt(args[1]);

            int pojemniki = liczbaKlockow / ileSieMiesci;
            int reszta = liczbaKlockow % ileSieMiesci;
            if (reszta != 0) pojemniki++;

            System.out.println("Potrzeba " + pojemniki + " pojemnikow");
            System.out.println("W ostatnim pojemniku jest " + reszta + " klockow");
        } catch (Exception e) {
            System.out.println("Bledne argumenty");
            System.exit(1);
        }
        
    }
}

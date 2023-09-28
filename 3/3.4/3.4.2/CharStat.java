import java.util.*;
import java.io.*;

public class CharStat {
    
    private File file;

    private HashMap chars;

    public CharStat(String fileName) throws FileNotFoundException, IOException {
        file = new File(fileName);
        chars = new HashMap(40);

        if (!file.isFile()) 
            throw new IllegalArgumentException("nie plik: " + fileName);

        FileReader fr = new FileReader(file);
        int c;

        while ((c = fr.read()) != -1) {
            Character ch = new Character( (char) c );
            Counter cnt = (Counter) chars.get(ch);
            if (cnt == null)
                chars.put(ch, new Counter());
            else
                cnt.incr();
        }
    }

    public Map.Entry[] sort(Comparator comp) {
        Set entries = chars.entrySet();
        Map.Entry[] map = (Map.Entry[]) entries.toArray(new Map.Entry[0]);
        Arrays.sort(map, comp);
        
        return map;
    }

    public static Comparator valComp = new Comparator() {
        
        public int compare(Object left, Object right) {
            Counter leftCounter = (Counter) ( (Map.Entry) left).getValue();
            Counter rightCounter = (Counter) ( (Map.Entry) right).getValue();
            return rightCounter.getCount() - leftCounter.getCount();
        }

        public boolean equals(Object o) {
            return (compare(this, o) == 0);
        }

        public String toString() {
            return "Sortowanie liczba wystapien";
        }
    };

    public static Comparator keyComp = new Comparator() {
        
        public int compare(Object left, Object right) {
            Character leftCounter = (Character) ( (Map.Entry) left).getKey();
            Character rightCounter = (Character) ( (Map.Entry) right).getKey();
            return leftCounter.compareTo(rightCounter);
        }

        public boolean equals(Object o) {
            return (compare(this, o) == 0);
        }

        public String toString() {
            return "Sortowanie znakami";
        }
    };

    public void printSorted(Comparator comp) {
        System.out.println("Plik " + file.getName() + ", " + comp);
        System.out.println(printString(sort(comp)));
    }

    public String toString() {
        String retval = "Plik " + file.getName() + "\n";
        Set entries = chars.entrySet();
        Map.Entry[] map = (Map.Entry[]) entries.toArray(new Map.Entry[0]);
        
        return retval + printString(map);
    }

    private static String printString(Map.Entry[] maps) {
        String retval = "";
        for (int i = 0; i < maps.length; i++) {
            Map.Entry entry = (Map.Entry) maps[i];
            Character chr = (Character) entry.getKey();
            Counter cnt = (Counter) entry.getValue();
            String chrStr = chr.toString();
            switch (chr.charValue()) {
                case '\n':
                    chrStr = "'\\n'";
                    break;
                case '\r':
                    chrStr = "'\\r'";
                    break;
                default:
                    chrStr = "'" + chrStr + "' ";
            }
            retval += (chrStr + " : " + cnt + "\n");
        }
        return retval;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Za malo argumentow");
            return;
        }
        CharStat cs;
        try {
            cs = new CharStat(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Nie odnaleziono pliku: " + args[0]);
            return;
        } catch (IOException e) {
            System.out.println("Blad wejscia-wyjscia: " + e.getMessage());
            return;
        }
        System.out.println(cs);
        cs.printSorted(keyComp);
        cs.printSorted(valComp);
    }
}

import java.util.*;
import java.io.*;

public class KonsolaSort {
    
    public static void main(String[] args) {
        Reader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        ArrayList lista = new ArrayList();
        String str = " ";
        System.out.println("Wprowadz ciag slow - po kazdym slowie nacisnij ENTER");

        while ( !str.equals("") ) {
            try {
                str = br.readLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            lista.add(str);
        }

        Collections.sort(lista);

        System.out.println("Posortowane elementy:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }
}

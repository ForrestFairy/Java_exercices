import java.io.*;
import java.util.*;

public class FileRev {

    private String nazwaPliku;
    private BufferedReader inputBuffer;
    private ArrayList wyrazy = new ArrayList();
    private String outputFileName;
    private BufferedWriter outputBuffer;
    private ArrayList outputFileCont = new ArrayList();
    private String fileExt = ".txt";

    public FileRev(String plik) {
        nazwaPliku = plik;
        outputFileName = outFileName(plik);
        getFiles();
        readInput();
        reverse();
        writeOutput();
    }

    private String outFileName(String inFileName) {
        if (inFileName.endsWith(fileExt)) {
            int ext = inFileName.length() - fileExt.length();
            String nazwa = inFileName.substring(0, ext);
            StringBuffer base = new StringBuffer(nazwa);
            String rev = new String(base.reverse());
            
            return rev + fileExt;
        }
        return new String(new StringBuffer(inFileName).reverse());
    }

    private void getFiles() {
        try {
            FileReader fr = new FileReader(nazwaPliku);
            inputBuffer = new BufferedReader(fr);

            FileWriter fw = new FileWriter(outputFileName);
            outputBuffer = new BufferedWriter(fw);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.exit(-2);
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
            e.printStackTrace();
            System.exit(-3);
        }
    }

    public void readInput() {
        try {
            String line;
            while ((line = inputBuffer.readLine()) != null) {
                wyrazy.add(line);
            }
        } catch (IOException e) {
            System.out.println("Blad oddczytu pliku: " + nazwaPliku + " " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void reverse() {
        for (int i = 0; i < wyrazy.size(); i++) {
            String newLine = "";
            String oldLine = (String) wyrazy.get(i);
            StringTokenizer st = new StringTokenizer(oldLine, " \t", true);
            while (st.hasMoreTokens()) {
                newLine = st.nextToken() + newLine; 
            }
            outputFileCont.add(0, newLine);
        }
    }

    public void writeOutput() {
        int lineNumber = 0;
        try {
            for ( ; lineNumber < outputFileCont.size(); lineNumber++) {
                String line = (String) outputFileCont.get(lineNumber);
                outputBuffer.write(line);
                outputBuffer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Blad zapisu do pliku: " +
                                outputFileName + " " +
                                e.getMessage()
                                );
            e.printStackTrace();
        }
        try {
            outputBuffer.close();
        } catch (IOException e) {
            System.out.println("Blad zamkniecia pliku: " +
                                outputFileName + " " +
                                e.getMessage()
                                );
            e.printStackTrace();
        }
        System.out.println("Zapisano " + lineNumber + " wiersz" +
                            (lineNumber == 1 ? "" : (lineNumber > 1 && lineNumber < 5 ? "e" : "y")) +
                            " do pliku " + outputFileName);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Za maalo argumentow");
            return;
        }
        new FileRev(args[0]);
    }
}
import java.io.*;

public class WordCount {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Za malo argumentow !");
        } 
        else if (args.length == 1) {
            new WordCount(args[0]).printRec();
        } 
        else if (args.length == 2) {
            new WordCount(args[0], args[1]).save();
        }
    }

    private String pattern;
    private DirEntry results;

    public WordCount(String fName) {
        try {
            FileInputStream fis = new FileInputStream(fName + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.results = (DirEntry) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("IOException w WordCount() " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException w WordCount() " + e.getMessage());
        }
    }

    public WordCount(String word, String dirName) {
        pattern = word;
        File dir = new File(dirName);
        if (dir.isDirectory()) {
            results = getDir(dir);
        }
        else {
            throw new IllegalArgumentException("To nie katalog: " + dir.getName());
        }
    }

    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream(pattern + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(results);
            oos.close();
        } catch (IOException e) {
            System.out.println("IOException w save() " + e.getMessage());
        }
        System.out.println("Wynik zapisano do pliku " + pattern + ".ser");
    }

    private DirEntry getDir(File dir) {
        System.out.println("Szukam w katalogu " + dir.getPath());
        File[] files = dir.listFiles();
        Entry[] entries = new Entry[files.length];
        for (int i = 0; i < entries.length; i++) {
            File curFile = files[i];
            if (curFile .isDirectory()) {
                entries[i] = getDir(curFile);
            }
            else {
                entries[i] = getRec(curFile);
            }
        }
        return new DirEntry(dir, entries);
    }

    private Entry getRec(File file) {
        int count = 0;
        try {
            StreamTokenizer st = new StreamTokenizer(
                new BufferedReader(
                    new FileReader(file)));
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                switch (st.ttype) {
                    case StreamTokenizer.TT_WORD:
                        if (st.sval.equals(pattern)) {
                            count++;
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException w getRec(): " + e.getMessage());
        }
        return new Entry(file, count);
    }

    public void printRec() {
        System.out.println(this.results);
    }
}

class Entry implements Serializable {
    protected File file;
    protected int count;

    Entry(File f, int n) {
        file = f;
        count = n;
    }

    public String toString() {
        return "Plik " + file.getPath() + " wystapien: " + count;
    }
}

class DirEntry extends Entry {
    protected Entry[] subDir;

    DirEntry(File f, Entry[] sub) {
        super(f, 0);
        subDir = sub;

        for (int i = 0; i < sub.length; i++) {
            count += sub[i].count;
        }
    }

    public String toString() {
        String value = "Katalog " + file.getPath() +
            " plikow " + subDir.length +
            " wystapien " + count;
        for (int i = 0; i < subDir.length; i++) {
            value += ("\n" + subDir[i]);
        }
        return value;
    }
}
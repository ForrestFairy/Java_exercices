import java.io.*;
import java.util.*;

public class Grep {

    // Rzeczy związane z interfejsem
    private interface SearchAction {
    
        /*
         * file - biezacy plik
         * lNum - numer wiersza
         * line - wiersz
         */
        void doIt(File file, int lNum, String line);
    }

    private SearchAction findWholeWords = 
        new SearchAction() {
            public void doIt(File file, int lNum, String line) {
                int pos = 0;
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreTokens()) {
                    if (word.equals(st.nextToken())) {
                        report(file, lNum, pos, line);
                    }
                    pos++;
                }
            }
        };
    private SearchAction findSubStrings =
        new SearchAction() {
            public void doIt(File file, int lNum, String line) {
                int startIndex = -1;
                while ( (startIndex = line.indexOf(word, ++startIndex)) != -1) {
                    report(file, lNum, startIndex, line);
                }
            }
        };

    // Teraz porządnie grep jest inicjowany

    private String word;
    private FileFilter filter;
    private File root;
    private SearchAction defaultSearchAction = findWholeWords;

    public Grep(String word) {
        this(word, ".");
    }
    public Grep(String word, String filePath) {
        this(word, filePath, "");
    }
    public Grep(String word, String filePath, final String ext) {
        this(word, filePath, new FileFilter() {
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                else {
                    return file.getPath().endsWith(ext);
                }
            }
        });
    }
    public Grep(String word, String filePath, FileFilter filtr) {
        this.word = word;
        filter = filtr;
        root = new File(filePath);
    }

    public void list() {
        rgrep(root);
    }

    private void grep(File file) {
        System.out.println("Szukam w pliku: " + file.getPath());
        try {
            LineNumberReader lnr =
                new LineNumberReader(new FileReader(file));
            String line;
            while ( (line = lnr.readLine()) != null) {
                defaultSearchAction.doIt(file, lnr.getLineNumber(), line);
            }
        } catch (IOException e) {
           System.out.println(e.getMessage());
        }
    }

    private void rgrep(File file) {
        if (file.isFile()) {
            grep(file);
        }
        else if (file.isDirectory()) {
            File[] dir = file.listFiles(filter);
            for (int i = 0; i < dir.length; i++) {
                rgrep(dir[i]);
            }
        }
    }

    public Grep setWholeWordsMode(boolean mode) {
        if (mode) {
            defaultSearchAction = findWholeWords;
        }
        else {
            defaultSearchAction = findSubStrings;
        }
        return this;
    }
    
    private void report(File file, int lNum, int pos, String line) {
        System.out.println(
            file.getPath() + ":" + lNum + "," + pos + "  " + line
        );
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Grep slowo plik");
        } else if (args.length == 1) {
            System.out.println("Przeszukiwanie domyslnego katalogu (\".\") ");
            new Grep(args[0]).list();
        } else if (args.length == 2) {
            System.out.println("Wypisz cale slowa:");
            Grep grep = new Grep(args[0], args[1]);
            grep.list();
            System.out.println("Wypisz wystapienia podslow:");
            grep.setWholeWordsMode(false).list();
        } else if (args.length == 3) {
            System.out.println("Przeszukiwanie plikow z rozszerzeniem " + args[2]);
            new Grep(args[0], args[1], args[2]).list();
        } else {
            FileFilter fileFilter =
                new FileFilter() {
                    public boolean accept(File file) {
                        if (file.isDirectory()) {
                            return true;
                        }
                        String name = file.getPath();
                        if (name.endsWith(".java") ||
                            name.endsWith(".txt") ||
                            name.endsWith(".htm") ||
                            name.endsWith(".html")) {
                                return true;
                            }
                        else {
                            return false;
                        }
                    }
                };
                new Grep(args[0], args[1], fileFilter).setWholeWordsMode(false).list();
        }
    }
}




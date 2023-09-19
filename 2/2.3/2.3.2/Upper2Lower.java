public class Upper2Lower {
    
    public static char toLower(char upper) {
        if (upper > 'Z' || upper < 'A') {
            return upper;
        }
        return (char) (upper + 32);
    }

    public static void main(String[] args) {
        char A = 'A';
        char nawiasL = (char) (A + 26);
        int jeden = 49;
        System.out.println(toLower(A));
        System.out.println(toLower('Z'));
        System.out.println(toLower(nawiasL));
        System.out.println(toLower((char)('A' - 1)));
        System.out.println(toLower((char)('A' + 1)));
        System.out.println(toLower((char) jeden));
    }
}
